package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.ClassCourseTeacher;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.impl.CrudServiceImpl;

import java.util.Date;

/**
 * Created by kangtaiyang on 2018/6/9.
 */
public class ClassCourseTeacherDaoTest extends BaseTest {

    @Autowired
    private ClassCourseTeacherDao classCourseTeacherDao;

    @Test
    public void testAddCc() {
        ClassCourseTeacher cct = new ClassCourseTeacher(new Class(1), new Course(3), sdf.format(new Date()));
        System.out.println(cct);
        int i = classCourseTeacherDao.addCc(cct);
        System.out.println(i);
    }

    @Test
    public void testDelCc() {
        ClassCourseTeacher cct = new ClassCourseTeacher(new Class(1), new Course(3));
        int i = classCourseTeacherDao.delCc(cct);
        System.out.println(i);
    }

    @Test
    public void testSetTeacherByCc() {
        ClassCourseTeacher cct = new ClassCourseTeacher(new Class(1), new Course(3), new PersonInfo(6), sdf.format(new Date()));
        int i = classCourseTeacherDao.setTeacherByCc(cct);
        System.out.println(i);
    }

    @Test
    public void testCctlist() {
        System.out.println(classCourseTeacherDao.cctlist());
    }

    @Test
    public void testQueryCourseByClass() {
        System.out.println(classCourseTeacherDao.queryCourseByClass(new Class(1)));
    }

    @Test
    public void testGetTeacherByCc() {
        System.out.println(classCourseTeacherDao.getTeacherByCc(new ClassCourseTeacher(new Class(1), new Course(3))));
    }

    @Test
    public void testGetCcByTeacher() {
        System.out.println(classCourseTeacherDao.getCcByTeacher(new PersonInfo(6)));
    }
}
