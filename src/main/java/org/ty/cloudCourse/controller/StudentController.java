package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ty.cloudCourse.dao.GiftDao;
import org.ty.cloudCourse.dao.StudentDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.service.ClassService;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.PersonService;
import org.ty.cloudCourse.service.StudentService;
import org.ty.cloudCourse.util.MD5;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/17.
 */
@Controller
@RequestMapping("student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private StudentDao studentDao;

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    private StudentInfo login(String userName, String password) {
        StudentInfo student = new StudentInfo();
        student.setUserName(userName);
        student.setPassword(password);
        AllExecution execution = studentService.login(student);
        if (execution.getState() == 1) {
            return (StudentInfo) execution.getA();
        }
        return new StudentInfo();
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
     * 修改密码
     *
     * @param studentId
     * @param password
     * @return
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.GET)
    @ResponseBody
    protected String changePassword(Integer studentId, String password) {
        StudentInfo student = new StudentInfo();
        student.setStudentId(studentId);
        student.setPassword(password);
        AllExecution execution = studentService.changePassword(student, studentDao);
        return (execution.getState() == 1 ? "success" : "error");
    }

    /**
     * 学生注册
     *
     * @param student
     * @param model
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    private String studentAdd(StudentInfo student, Model model) {
        student.setPassword(MD5.md5(student.getPassword()));
        student.setEnableStatus(1);
        AllExecution execution = crudService.insertOne(student, studentDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @Autowired
    private ClassService classService;
    @Autowired
    private PersonService personService;

    /**
     * 通过班级查看班级学生
     *
     * @param classId
     * @param url
     * @param model
     * @return
     */
    @RequestMapping(value = "queryStuByCla", method = RequestMethod.POST)
    private String queryStuByClass(Integer classId, String url, Model model) {
        AllExecution execution = classService.queryStudentByClass(new Class(classId));
        model.addAttribute("msg", execution.getStateInfo());
        model.addAttribute("stuList", execution.getAList());
        List<PersonInfo> schlist = personService.querySchool().getAList();
        model.addAttribute("schList", schlist);
        return url;
    }

    /**
     * 更新页面跳转
     *
     * @param stuId
     * @param model
     * @return
     */
    @RequestMapping(value = "updt", method = RequestMethod.GET)
    private String updt(int stuId, Model model) {
        if (stuId != 0) {
            AllExecution execution = crudService.getOneById(new StudentInfo(stuId), studentDao);
            StudentInfo student = (StudentInfo) execution.getA();
            model.addAttribute("student", student);
        }
        return "student/update";
    }

    /**
     * 更新学生用户
     *
     * @param student
     * @param model
     * @return
     */
    @RequestMapping(value = "updtStu", method = RequestMethod.POST)
    private String updtPerson(StudentInfo student, Model model) {
        if (student.getEnableStatus() == null) student.setEnableStatus(0);
        AllExecution execution = crudService.updateOne(student, studentDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @RequestMapping(value = "hwlist", method = RequestMethod.GET)
    @ResponseBody
    private List<Homework> homeworkList(Integer studentId) {
        StudentInfo student = studentDao.getOneById(new StudentInfo(studentId));
        List<Homework> hwlist = studentService.queryHomeworkByStudentAndTime(student, sdf.format(new Date())).getAList();
        return hwlist;
    }

    @Autowired
    private GiftDao giftDao;

    @RequestMapping(value = "giftlist", method = RequestMethod.GET)
    @ResponseBody
    private List<Gift> giftList(Integer studentId) {
        StudentInfo student = studentDao.getOneById(new StudentInfo(studentId));
        return giftDao.getGiftListByClass(student.getClazz());
    }

    @RequestMapping(value = "getGift", method = RequestMethod.GET)
    @ResponseBody
    private String getGift(Integer studentId, Integer giftId) {
        Gift gift = (Gift) giftDao.getOneById(giftId);
        if (gift.getGiftNum() <= 0) {
            return "nogift";
        }
        StudentInfo student = (StudentInfo) studentDao.getOneById(studentId);
        if (student.getPointCount() < gift.getGiftPoint()) {
            return "nopoint";
        } else {
            student.setPointCount(student.getPointCount() - gift.getGiftPoint());
            studentDao.updateOne(student);
            gift.setGiftNum(gift.getGiftNum() - 1);
            giftDao.updateOne(gift);
            return "success";
        }
    }

    @RequestMapping(value = "point", method = RequestMethod.GET)
    @ResponseBody
    private String getpoint(Integer studentId) {
        StudentInfo student = (StudentInfo) studentDao.getOneById(studentId);
        return "" + student.getPointCount();
    }
}