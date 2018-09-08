package org.ty.cloudCourse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.ty.cloudCourse.dao.*;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.enums.UserStateEnum;
import org.ty.cloudCourse.service.ClassService;
import org.ty.cloudCourse.service.CourseService;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.PersonService;
import org.ty.cloudCourse.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * Created by kangtaiyang on 2018/5/17.
 */
@Controller
@RequestMapping("person")
public class PersonController extends BaseController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil syncUtil;

    // CHECK(0, "审核中"), OFFLINE(-1, "非法用户"), LOGIN_ERROR(-2, "登陆失败"), PASS(2, "通过认证"), SUCCESS(1, "操作成功"),
    //     INNER_ERROR(-1001, "内部系统错误"), NULL_ID(-1002, "Id为空"), NULL_MESS(-1003, "用户信息为空");

    /**------------------------共有方法------------------------**/

    /**
     * 用户登录
     *
     * @param person
     * @param model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    private String login(PersonInfo person, String code, HttpServletRequest request, HttpSession session, Model model) {
        if (!code.toLowerCase().equals(("" + session.getAttribute("checkcode")).toLowerCase())) {
            request.setAttribute("codeError", "验证码输入错误");
            return "login";
        }
        AllExecution execution = personService.login(person);
        model.addAttribute("msg", execution.getStateInfo());
        if (execution.getState() == 1) {
            PersonInfo user = (PersonInfo) (execution.getA());
            session.removeAttribute("userId");
            session.removeAttribute("userName");
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            model.addAttribute("msg", execution.getStateInfo());
            model.addAttribute("person", user);
            return user.getUserType() == 0 ? "person/admin_index" : (user.getUserType() == 1 ? "person/school_index" : "person/teacher_index");
        }
        return "error";
    }

    /**
     * 退出当前账户
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "exit", method = RequestMethod.GET)
    protected String exit(HttpSession session) {
        return super.exit(session);
    }

    /**
     * 后端用户首页
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "shouye", method = RequestMethod.GET)
    private String shouye(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("userId");
        AllExecution execution = crudService.getOneById(new PersonInfo(id), personDao);
        if (execution.getState() == 1) {
            int type = ((PersonInfo) execution.getA()).getUserType();
            model.addAttribute("person", execution.getA());
            if (type == 0) return "person/admin_index";
            if (type == 1) return "person/school_index";
            if (type == 2) return "person/teacher_index";
        }
        return "login";
    }

    /**
     * 修改密码
     *
     * @param person
     * @param model
     * @return
     */
    @RequestMapping(value = "passwd", method = RequestMethod.POST)
    protected String changePassword(PersonInfo person, String npasswd, HttpSession session, Model model) {
        String opasswd = person.getPassword();
        person.setUserId(Integer.parseInt("" + session.getAttribute("userId")));
        PersonInfo o = (PersonInfo) crudService.getOneById(person, personDao).getA();
        if (!o.getPassword().equals(MD5.md5(opasswd))) {
            model.addAttribute("msg", "原密码输入错误！");
            return "error";
        }
        person.setPassword(npasswd);
        AllExecution execution = personService.changePassword(person, personDao);
        if (execution.getState() == -1001) {
            model.addAttribute("msg", execution.getStateInfo());
            return "error";
        }
        Map map = new HashMap();
        map.put("password", ((PersonInfo) execution.getA()).getPassword());
        redisFactory.hmset(new Tuple(syncUtil.getTypeAndKeyFromEntity((BaseEntity) execution.getA()).getB(), map));
        model.addAttribute("msg", UserStateEnum.SUCCESS.getStateInfo());
        return "success";
    }


    /**
     * 添加/修改下级用户
     *
     * @param person
     * @param actionType(0添加,1修改)
     * @param model
     * @return
     */
    @RequestMapping(value = "cuPerson", method = RequestMethod.POST)
    private String cuPerson(PersonInfo person, Integer actionType, MultipartFile headImgFile, HttpSession session, Model model) {
        AllExecution execution = null;
        if (actionType == 0) {
            if (headImgFile == null) {
                model.addAttribute("msg", "头像未上传！");
                return "error";
            }
            person.setPassword(MD5.md5(person.getPassword()));

            if (person.getParentInfo() == null) {
                person.setParentInfo(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
            }
            execution = getExecutionByActionType(person, actionType, personDao, model);
        }
        // 上传头像部分
        if (execution.getState() == 1) {
            String fileName = headImgFile.getOriginalFilename();
            if (Arrays.asList(FileUtil.imgFormat).contains(fileName.substring(fileName.lastIndexOf("."), fileName.length()))) {
                String path = PathUtil.getImgBasePath() + PathUtil.getHeadImgPath(person.getUserId());
                try {
                    FileUtil.fileUpload(headImgFile, new File(path));
                    person.setUserHeadImg(path + headImgFile.getOriginalFilename());
                    crudService.updateOne(person, personDao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                model.addAttribute("msg", "上传文件格式错误，应为 " + Arrays.asList(FileUtil.imgFormat));
            }
        }
        return execution.getState() == 1 ? "success" : "error";
    }

    /**
     * 更新后端用户
     *
     * @param person
     * @param model
     * @return
     */
    @RequestMapping(value = "updtPerson", method = RequestMethod.POST)
    private String updtPerson(PersonInfo person, Integer parent_id, Model model) {
        person.setParentInfo(new PersonInfo(parent_id));
        AllExecution execution = getExecutionByActionType(person, 1, personDao, model);
        return execution.getState() == 1 ? "success" : "error";
    }

    /**
     * 查看/删除下级用户
     *
     * @param personId
     * @param actionType(2查看,3删除)
     * @param model
     * @return
     */
    @RequestMapping(value = "rdPerson", method = RequestMethod.GET)
    private String rdPerson(Integer personId, Integer actionType, Model model) {
        AllExecution execution = getExecutionByActionType(new PersonInfo(personId), actionType, personDao, model);
        if (execution.getState() == 1) {
            if (actionType == 3) return "success";
            model.addAttribute("person", execution.getA());
            return "person/person_show";
        } else return "error";
    }

    /**
     * 传入用户返回其下所有下级用户
     *
     * @param personId
     * @param model
     * @return
     */
    @RequestMapping(value = "oneToMany", method = RequestMethod.POST)
    private String oneToMany(Integer personId, String rturl, Model model) {
        if (personId == -1) {
            List<PersonInfo> schList = personService.querySchool().getAList();
            model.addAttribute("schList", schList);
            model.addAttribute("teaList", personDao.queryPersonByType(2));
            return rturl;
        }
        PersonInfo person = (PersonInfo) personDao.getOneById(new PersonInfo(personId));
        AllExecution execution = null;
        if (person.getUserType() == null) {
            person.setUserType(personDao.getOneById(new PersonInfo(personId)).getUserType());
        }
        if (person.getUserType() == 0) execution = personService.querySchool();
        if (person.getUserType() == 1) execution = personService.queryTeacherBySchool(person);
        if (execution.getState() == 1) {
            if (person.getUserType() == 0) {
                model.addAttribute("schList", execution.getAList());
            } else if (person.getUserType() == 1) {
                List<PersonInfo> schList = personService.querySchool().getAList();
                model.addAttribute("schList", schList);
                model.addAttribute("teaList", execution.getAList());
            }
            return rturl;
        } else {
            model.addAttribute("msg", execution.getStateInfo());
            return "error";
        }
    }

    /**
     * 图片流加载
     *
     * @param url
     * @param response
     * @return
     */
    @RequestMapping(value = "img", method = RequestMethod.GET)
    private void showPhoto(String url, HttpServletResponse response) {
        logger.info("图片请求路径 " + url);
        logger.info("图片请求response"+response);
        response.setContentType("image/jpeg");
        InputStream is = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            is = new FileInputStream(url);
            byte[] bytes = new byte[100];
            while (is.read(bytes) != -1) {
                os.write(bytes, 0, bytes.length);
            }
        } catch (Exception e) {
            logger.error("头像请求异常 ",e);
            System.out.println("用户头像路径找不到");
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**---------------------超级管理员方法---------------------**/
    /**
     * 学校管理
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "schlist", method = RequestMethod.GET)
    private String schlist(Model model) {
        // 查询列表处
        AllExecution execution = personService.querySchool();
        List<PersonInfo> schoolList = execution.getAList();
        model.addAttribute("schoolList", schoolList);

        // 新建处
        List<PersonInfo> list = personDao.queryPersonByType(0);
        if (list != null) {
            model.addAttribute("list", list);
        }
        return "school/list";
    }

    /**
     * 更新页面跳转
     *
     * @param personId
     * @param model
     * @return
     */
    @RequestMapping(value = "updt", method = RequestMethod.GET)
    private String updt(int personId, Model model) {
        if (personId != 0) {
            AllExecution execution = crudService.getOneById(new PersonInfo(personId), personDao);
            PersonInfo person = (PersonInfo) execution.getA();
            model.addAttribute("person", person);
            List<PersonInfo> list = personDao.queryPersonByType(person.getUserType() - 1);
            if (list != null) {
                model.addAttribute("list", list);
            }
        }
        return "person/update";
    }

    /**
     * 跳转到教师管理页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "tealist", method = RequestMethod.GET)
    private String tealist(String url, HttpSession session, Model model) {
        if (url.equals("teacher/list")) {
            List<PersonInfo> teaList = personDao.queryPersonByType(2);
            List<PersonInfo> schList = personDao.queryPersonByType(1);
            model.addAttribute("teaList", teaList);
            model.addAttribute("schList", schList);
        } else if (url.equals("teacher/tealist")) {
            List<PersonInfo> teaList = personDao.queryTeacherBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
            model.addAttribute("teaList", teaList);
        }
        return url;
    }

    @RequestMapping(value = "stulist", method = RequestMethod.GET)
    private String stulist(Model model) {
        List<StudentInfo> stuList = studentDao.queryStudent();
        for (StudentInfo stu :
                stuList) {
            try {
                Class c = stu.getClazz();
                c = classDao.getOneById(c);
                stu.setSchool(c.getSchool());
            } catch (RuntimeException e) {
                System.out.println("person/stulist  抛出运行时异常");
            }
        }
        model.addAttribute("stuList", stuList);
        List<PersonInfo> schlist = personService.querySchool().getAList();
        model.addAttribute("schList", schlist);
        return "student/list";
    }

    @RequestMapping(value = "studel", method = RequestMethod.GET)
    private String del(Integer stuId, Model model) {
        AllExecution execution = crudService.deleteOne(new StudentInfo(stuId), studentDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @RequestMapping(value = "ajaxGetClassBySchool", method = RequestMethod.GET)
    private String ajaxGetClassBySchool(Integer personId, Model model) throws JsonProcessingException {
        List<Class> clalist = classDao.getClassBySchool(new PersonInfo(personId));

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(clalist);
        model.addAttribute("json", json);
        return "json";
    }

    /**
     * ----------------------培训机构方法----------------------
     **/

    @Autowired
    private ClassService classService;

    @RequestMapping(value = "clalist", method = RequestMethod.GET)
    private String clalist(Integer schoolId, Model model) {
        List<Class> classList = classDao.getClassBySchool(new PersonInfo(schoolId));
        model.addAttribute("clalist", classList);
        return "class/list";
    }

    @RequestMapping(value = "claupdt", method = RequestMethod.GET)
    private String claupdt(Integer classId, Model model) {
        AllExecution execution = crudService.getOneById(new Class(classId), classDao);
        model.addAttribute("cla", execution.getA());
        return "class/update";
    }

    @RequestMapping(value = "claupdate", method = RequestMethod.POST)
    private String claupdate(Class clazz, Model model) {
        AllExecution execution = crudService.updateOne(clazz, classDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCategoryDao categoryDao;
    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "coulist", method = RequestMethod.GET)
    private String coulist(HttpSession session, Model model) {
        List<CourseCategory> categoryList = categoryDao.getCategoryBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("catlist", categoryList);
        List<Course> courseList = new ArrayList<>();
        categoryList.forEach(x -> courseList.addAll(courseService.queryCourseByCourseCategory(x).getAList()));
        model.addAttribute("coulist", courseList);
        return "course/list";
    }

    @RequestMapping(value = "couupdt", method = RequestMethod.GET)
    private String couupdt(HttpSession session, Integer courseId, Model model) {
        AllExecution execution = crudService.getOneById(new Course(courseId), courseDao);
        model.addAttribute("course", execution.getA());
        List<CourseCategory> categoryList = categoryDao.getCategoryBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("catlist", categoryList);
        return "course/update";
    }

    /**
     * ------------------------教师方法------------------------
     **/

    @Autowired
    private ClassCourseTeacherDao cctDao;
    @Autowired
    private HomeworkDao homeworkDao;

    @RequestMapping(value = "hwlist", method = RequestMethod.GET)
    private String hwlist(HttpSession session, Model model) {
        PersonInfo teacher = new PersonInfo(Integer.parseInt("" + session.getAttribute("userId")));
        List<ClassCourseTeacher> cctlist = cctDao.getCcByTeacher(teacher);
        for (ClassCourseTeacher cct :
                cctlist) {
            Homework h = new Homework();
            h.setCourse(cct.getCourse());
            h.setClazz(cct.getClazz());
            h.setTeacher(cct.getTeacher());
            h.setCreateTime(sdf.format(new Date()));
            int i = homeworkDao.getHWByWhere(h).size();
            cct.setEditTime("" + i);
        }
        model.addAttribute("cctlist", cctlist);
        return "homework/list";
    }

    @RequestMapping(value = "hwupdt", method = RequestMethod.GET)
    private String updt(Integer classId, Integer courseId, Integer teacherId, Model model) {
        Homework homework = new Homework();
        homework.setClazz(new Class(classId));
        homework.setCourse(new Course(courseId));
        homework.setTeacher(new PersonInfo(teacherId));
        homework.setCreateTime(sdf.format(new Date()));
        List<Homework> l = homeworkDao.getHWByWhere(homework);
        Homework h = null;
        if (l.size() != 0) {
            h = (Homework) l.get(0);
        } else {
            h = new Homework();
            h.setClazz(classDao.getOneById(new Class(classId)));
            h.setCourse(courseDao.getOneById(new Course(courseId)));
        }
        model.addAttribute("hw", h);
        Homework h2 = new Homework();
        h2.setTeacher(new PersonInfo(teacherId));
        h2.setClazz(new Class(classId));
        h2.setCourse(new Course(courseId));
        List<Homework> hwlist = homeworkDao.getHWByWhere(h2);
        model.addAttribute("hwlist", hwlist);
        model.addAttribute("today", sdf.format(new Date()));
        return "homework/update";
    }

    @RequestMapping(value = "hwupdate", method = RequestMethod.POST)
    private String hwupdate(Homework homework, Integer classId, Integer courseId, HttpSession session, Model model) {
        Class clazz = new Class(classId);
        Course course = new Course(courseId);
        PersonInfo teacher = new PersonInfo(Integer.parseInt("" + session.getAttribute("userId")));
        homework.setCourse(course);
        homework.setClazz(clazz);
        homework.setTeacher(teacher);
        Homework h = new Homework();
        h.setCourse(course);
        h.setClazz(clazz);
        h.setTeacher(teacher);
        Homework h2 = new Homework();
        try {
            h2 = homeworkDao.getHWByWhere(h).get(0);
        } catch (RuntimeException e) {
            System.out.println("新数据");
        }
        int i = 0;
        if (homework.getCreateTime() != sdf.format(new Date())) {
            System.out.println("执行插入，hw=" + homework);
            i = homeworkDao.insertOne(homework);
        } else {
            homework.setHomeworkId(h2.getHomeworkId());
            System.out.println("hw=" + homework);
            i = homeworkDao.updateOne(homework);
        }
        return i != 0 ? "success" : "error";
    }

    @Autowired
    private SignDao signDao;

    @RequestMapping(value = "signlist", method = RequestMethod.GET)
    private String signlist(HttpSession session, Model model) {
        PersonInfo teacher = new PersonInfo(Integer.parseInt("" + session.getAttribute("userId")));
        List<ClassCourseTeacher> cctlist = cctDao.getCcByTeacher(teacher);
        model.addAttribute("cctlist", cctlist);
        return "sign/list";
    }

    @RequestMapping(value = "sign", method = RequestMethod.GET)
    private String sign(Integer classId, Integer courseId, Model model) {
        ClassSign cs = new ClassSign();
        cs.setClazz(new Class(classId));
        cs.setCourse(new Course(courseId));
        List<ClassSign> cslist = signDao.getCsByWhere(cs);
        model.addAttribute("cslist", cslist);
        System.out.println(cslist);
        return "sign/list2";
    }

    @Autowired
    private GiftDao giftDao;

    @RequestMapping(value = "giftlist", method = RequestMethod.GET)
    private String giftlist(HttpSession session, Model model) {
        PersonInfo teacher = new PersonInfo(Integer.parseInt("" + session.getAttribute("userId")));
        List<ClassCourseTeacher> cctlist = cctDao.getCcByTeacher(teacher);
        List<Gift> giftList = new ArrayList<>();
        for (ClassCourseTeacher cct :
                cctlist) {
            giftDao.getGiftListByClass(cct.getClazz()).forEach(x -> giftList.add(x));
        }
        model.addAttribute("giftList", giftList);
        System.out.println(giftList);
        return "gift/list";
    }

    @RequestMapping(value = "giftupdt", method = RequestMethod.GET)
    private String giftupdt(Integer giftId, Model model) {
        Gift gift = (Gift) giftDao.getOneById(giftId);
        model.addAttribute("gift", gift);
        return "gift/update";
    }

    @RequestMapping(value = "giftupdate", method = RequestMethod.POST)
    private String giftupdate(Gift gift, Model model) {
        System.out.println(gift);
        int i = giftDao.updateOne(gift);
        return i != 0 ? "success" : "error";
    }

    /*课堂辅助系统接口*/
    @RequestMapping(value = "dianming",method = RequestMethod.GET)
    private String dianming(Model model){
        return null;
    }
}
