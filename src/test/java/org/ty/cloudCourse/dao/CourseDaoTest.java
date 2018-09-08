package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class CourseDaoTest extends BaseTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    public void testInsertCourseBySchool() {
        Course course = new Course();
        course.setCourseCategory(new CourseCategory(1));
        course.setCourseDesc("我是数学课的描述啊");
        course.setCourseName("数学1");
        course.setCoursePrice(599.0);
        course.setEndTime("2018-09-10");
        course.setImgPath("test/test.jpg");
        course.setMaxStudentCount(30);
        course.setStartTime(sdf.format(new Date()));
        courseDao.insertOne(course);
    }

    @Test
    public void testUpdateCourse() {
        Course c = courseDao.getOneById(new Course(1));
        c.setEndTime(super.sdf.format(new Date()));
        courseDao.updateOne(c);
    }

    @Test
    public void testDeleteCourse() {
        courseDao.deleteOne(new Course(2));
    }

    @Test
    public void testQueryCourseByCategory(){
        List<Course> courseList = courseDao.queryCourseByCategory(new CourseCategory(1));
        System.out.println(courseList);
    }

    @Test
    public void testQueryCourseByTeacher() {
        List<Course> courseList = courseDao.queryCourseByTeacher(new PersonInfo(6));
        courseList.forEach(System.out::println);
    }
}
