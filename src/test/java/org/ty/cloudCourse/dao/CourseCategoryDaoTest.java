package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class CourseCategoryDaoTest extends BaseTest {

    @Autowired
    private CourseCategoryDao categoryDao;


    @Test
    public void testInsertCourseCategory() {
        CourseCategory category = new CourseCategory();
        category.setCategoryName("计算机类");
        category.setCategoryDesc("我是计算机类的课程类型");
        category.setCreateTime(sdf.format(new Date()));
        category.setLastEditTime(sdf.format(new Date()));
        category.setPriority(4);
        category.setSchool(new PersonInfo(5));
        categoryDao.insertOne(category);
    }

    @Test
    public void testDeleteCourseCategory() {
        categoryDao.deleteOne(new CourseCategory(3));
    }

    @Test
    public void testGetCategoryById() {
        CourseCategory category = categoryDao.getOneById(new CourseCategory(2));
        System.out.println(category);
    }

    @Test
    public void testGetCategoryBySchool() {
        PersonInfo school = new PersonInfo();
        school.setUserId(5);
        List<CourseCategory> categoryList = categoryDao.getCategoryBySchool(school);
        System.out.println(categoryList);
    }

    @Test
    public void testUpdateCategory() {
        CourseCategory category = categoryDao.getOneById(new CourseCategory(2));
        System.out.println(category);
        category.setPriority(8);
        category.setLastEditTime(sdf.format(new Date()));
        category.setCategoryDesc("我是新的描述啊");
        categoryDao.updateOne(category);
    }
}
