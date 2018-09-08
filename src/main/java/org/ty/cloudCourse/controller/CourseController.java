package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.ty.cloudCourse.dao.CourseDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.service.CourseService;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.util.FileUtil;
import org.ty.cloudCourse.util.PathUtil;

import java.io.File;
import java.util.Arrays;

/**
 * @author kangtaiyang
 * @date 2018/6/21
 */
@Controller
@RequestMapping("course")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private CourseDao courseDao;

    /**
     * 新建/修改课程信息
     *
     * @param course
     * @param categoryId
     * @param actionType(0添加,1修改)
     * @param model
     * @return
     */
    @RequestMapping(value = "cuCourse", method = RequestMethod.POST)
    private String cuCourse(MultipartFile file, Course course, Integer categoryId, Integer actionType, Model model) {
        if (categoryId != null) course.setCourseCategory(new CourseCategory(categoryId));
        AllExecution execution = getExecutionByActionType(course, actionType, courseDao, model);
        if (execution.getState() == 1) {
            String fileName = file.getOriginalFilename();
            if (Arrays.asList(FileUtil.imgFormat).contains(fileName.substring(fileName.lastIndexOf("."), fileName.length()))) {
                String path = PathUtil.getImgBasePath() + PathUtil.getCoursePath(course.getCourseId());
                try {
                    FileUtil.fileUpload(file, new File(path));
                    course.setImgPath(path + file.getOriginalFilename());
                    crudService.updateOne(course, courseDao);
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
     * 独立更新的方法
     *
     * @param course
     * @return
     */
    @RequestMapping(value = "couupdate", method = RequestMethod.POST)
    private String couupdate(Course course, Integer categoryId, Model model) {
        if (categoryId != null) {
            course.setCourseCategory(new CourseCategory(categoryId));
        }
        AllExecution execution = crudService.updateOne(course, courseDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    /**
     * 查看/删除下级用户
     *
     * @param courseId
     * @param actionType(2查看,3删除)
     * @param model
     * @return
     */
    @RequestMapping(value = "rdCourse", method = RequestMethod.GET)
    private String rdCourse(Integer courseId, Integer actionType, Model model) {
        AllExecution execution = getExecutionByActionType(new Course(courseId), actionType, courseDao, model);
        if (execution.getState() == 1) {
            if (actionType == 3) return "success";
            model.addAttribute("course", execution.getA());
            return "person/person_show";
        } else return "error";
    }
}