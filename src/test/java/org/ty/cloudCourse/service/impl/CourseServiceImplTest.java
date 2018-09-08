package org.ty.cloudCourse.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.dao.CourseCategoryDao;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.service.CourseService;
import org.ty.cloudCourse.service.CrudService;

import static org.junit.Assert.*;

/**
 * @author kangtaiyang
 * @date 2018/7/7
 */
public class CourseServiceImplTest extends BaseTest {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private CourseCategoryDao categoryDao;

    @Test
    public void testQueryCourseByCourseCategory() {
        CourseCategory category = (CourseCategory) crudService.getOneById(new CourseCategory(1),categoryDao).getA();
        courseService.queryCourseByCourseCategory(category).getAList().forEach(System.out::println);
    }

}