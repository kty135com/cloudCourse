package org.ty.cloudCourse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ty.cloudCourse.dao.ClassDao;
import org.ty.cloudCourse.dao.CourseDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.ClassService;
import org.ty.cloudCourse.service.CourseService;
import org.ty.cloudCourse.service.CrudService;

/**
 * @author kangtaiyang
 * @date 2018/6/21
 */
@Controller
@RequestMapping("class")
public class ClassController extends BaseController {

    @Autowired
    private ClassService classService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private ClassDao classDao;

    /**
     * 新建/修改班级
     *
     * @param clazz
     * @param actionType(0添加,1修改)
     * @param model
     * @return
     */
    @RequestMapping(value = "cuClass", method = RequestMethod.POST)
    private String cuClass(Class clazz, Integer schoolId, Integer actionType, Model model) {
        if (schoolId != null) clazz.setSchool(new PersonInfo(schoolId));
        AllExecution execution = getExecutionByActionType(clazz, actionType, classDao, model);
        return execution.getState() == 1 ? "success" : "error";
    }

    /**
     * 查看/删除班级
     *
     * @param classId
     * @param actionType(2查看,3删除)
     * @param model
     * @return
     */
    @RequestMapping(value = "rdClass", method = RequestMethod.GET)
    private String rdClass(Integer classId, Integer actionType, Model model) {
        AllExecution execution = getExecutionByActionType(new Class(classId), actionType, classDao, model);
        if (execution.getState() == 1) {
            if (actionType == 3) return "success";
            model.addAttribute("class", execution.getA());
            return "class/class_show";
        } else return "error";
    }

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "ajaxGetCourseByCategory", method = RequestMethod.GET)
    private String ajaxGetCourseByCategory(Integer categoryId, Model model) {
        AllExecution execution = courseService.queryCourseByCourseCategory(new CourseCategory(categoryId));
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(execution.getAList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("json", json);
        return "json";
    }
}
