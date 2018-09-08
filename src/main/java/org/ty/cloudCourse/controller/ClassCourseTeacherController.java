package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ty.cloudCourse.dao.*;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.service.ClassCourseTeacherService;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.util.Tuple;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/6/22
 */
@Controller
@RequestMapping("cct")
public class ClassCourseTeacherController extends BaseController {

    @Autowired
    private CrudService crudService;
    @Autowired
    private ClassCourseTeacherDao cctDao;
    @Autowired
    private ClassCourseTeacherService cctService;

    /**
     * 新建，删除三者间两者的关系
     *
     * @param classId
     * @param courseId
     * @param actionType(0新建,3删除)
     * @param model
     * @return
     */
    @RequestMapping("cdcct")
    private String cdCct(Integer classId, Integer courseId, Integer actionType, Model model) {
        AllExecution execution = null;
        ClassCourseTeacher cct = new ClassCourseTeacher(new Class(classId), new Course(courseId), sdf.format(new Date()));
        if (actionType == 0) execution = cctService.addCc(cct);
        if (actionType == 3) execution = cctService.delCc(cct);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    /**
     * 更新教师与班级课程的关系
     *
     * @param classId
     * @param courseId
     * @param teacherId
     * @param model
     * @return
     */
    @RequestMapping(value = "ucct", method = RequestMethod.POST)
    private String uCct(Integer classId, Integer courseId, Integer teacherId, Model model) {
        AllExecution execution = cctService.setTeacherByCc(new ClassCourseTeacher(new Class(classId), new Course(courseId), new PersonInfo(teacherId), sdf.format(new Date())));
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @Autowired
    private ClassDao classDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseCategoryDao categoryDao;

    @RequestMapping(value = "cctlist", method = RequestMethod.GET)
    private String cctlist(HttpSession session, Model model) {
        List<ClassCourseTeacher> cctList = cctDao.cctlist();
        model.addAttribute("classCourseList", cctList);
        List<Class> classList = classDao.getClassBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("clalist", classList);
        List<CourseCategory> categoryList = categoryDao.getCategoryBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("catlist", categoryList);
        return "classcourse/list";
    }

    @RequestMapping(value = "ctlist", method = RequestMethod.GET)
    private String ctlist(HttpSession session, Model model) {
        AllExecution execution = cctService.cctListBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("cctlist", execution.getAList());
        return "courseteacher/list";
    }

    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "updt", method = RequestMethod.GET)
    private String updt(Integer classId, Integer courseId, Integer teacherId, HttpSession session, Model model) {
        Class clazz = classDao.getOneById(new Class(classId));
        Course course = courseDao.getOneById(new Course(courseId));
        PersonInfo teacher = null;
        if (teacherId != null) {
            teacher = personDao.getOneById(new PersonInfo(teacherId));
        }
        List<PersonInfo> teacherList = personDao.queryTeacherBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("clazz", clazz);
        model.addAttribute("course", course);
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherList", teacherList);
        return "courseteacher/update";
    }
}
