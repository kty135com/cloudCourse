package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.cloudCourse.dao.CourseCategoryDao;
import org.ty.cloudCourse.dao.CourseDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.enums.UserStateEnum;
import org.ty.cloudCourse.service.CourseService;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/17.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseCategoryDao categoryDao;

    @Override
    public AllExecution queryCourseByCourseCategory(CourseCategory category) {
        List<Course> courseList;
        try {
            courseList = courseDao.queryCourseByCategory(category);
        } catch (Exception e) {
            return innerError();
        }
        return new AllExecution(courseList, UserStateEnum.SUCCESS);
    }

}
