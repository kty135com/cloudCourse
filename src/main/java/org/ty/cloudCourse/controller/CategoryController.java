package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ty.cloudCourse.dao.CourseCategoryDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.CrudService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/6/21
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {

    @Autowired
    private CrudService crudService;
    @Autowired
    private CourseCategoryDao categoryDao;

    /**
     * 更新
     *
     * @param category
     * @param model
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    private String cuCategory(CourseCategory category, Model model) {
        category.setLastEditTime(sdf.format(new Date()));
        int i = categoryDao.updateOne(category);
        return i != 0 ? "success" : "error";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String add(CourseCategory category, HttpSession session, Model model) {
        String now = sdf.format(new Date());
        category.setCreateTime(now);
        category.setLastEditTime(now);
        if (category.getSchool() == null) {
            category.setSchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        }
        int i = categoryDao.insertOne(category);
        return i != 0 ? "success" : "error";
    }

    /**
     * 查看/删除课程类型
     *
     * @param categoryId
     * @param actionType(2查看,3删除)
     * @param model
     * @return
     */
    @RequestMapping(value = "rdCategory", method = RequestMethod.GET)
    private String rdCategory(Integer categoryId, Integer actionType, Model model) {
        AllExecution execution = getExecutionByActionType(new CourseCategory(categoryId), actionType, categoryDao, model);
        if (execution.getState() == 1) {
            if (actionType == 3) return "success";
            model.addAttribute("category", execution.getA());
            return "category/category_show";
        } else return "error";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    private String list(HttpSession session, Model model) {
        List<CourseCategory> categoryList = categoryDao.getCategoryBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("catlist", categoryList);
        return "category/list";
    }

    @RequestMapping(value = "updt", method = RequestMethod.GET)
    private String updt(Integer categoryId, Model model) {
        CourseCategory courseCategory = categoryDao.getOneById(new CourseCategory(categoryId));
        model.addAttribute("category", courseCategory);
        return "category/update";
    }
}
