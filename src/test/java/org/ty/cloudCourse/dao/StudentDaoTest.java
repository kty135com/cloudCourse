package org.ty.cloudCourse.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.StudentInfo;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class StudentDaoTest extends BaseTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void testQueryStudentByClass() {
        List<StudentInfo> studentList = studentDao.queryStudentByClass(new Class(1));
        System.out.println(studentList);
    }

    @Test
    public void testLogin() {
        StudentInfo student = new StudentInfo();
        student.setUserName("stu1");
        student.setPassword("1234");
        StudentInfo s = studentDao.login(student);
        System.out.println(s);
    }

    @Test
    public void testGetStudentById() {
        StudentInfo student = studentDao.getOneById(new StudentInfo(1));
        System.out.println(student);
    }

    @Test
    public void testInsertStudent() {
        StudentInfo student = new StudentInfo();
        student.setUserName("stu3");
        student.setPassword("1234");
        student.setEnableStatus(0);
   //     int i = studentDao.insertStudent(student);
   //     System.out.println(i);
    }

    @Test
    public void testDeleteStudent() {
        int i = studentDao.deleteOne(new StudentInfo(2));
        System.out.println(i);
    }

    @Test
    public void testUpdateStudent() {
        StudentInfo student = new StudentInfo();
        student.setStudentId(1);
        student.setPassword("123");
        int i = studentDao.updateOne(student);
        System.out.println(i);
    }
}
