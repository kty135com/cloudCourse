package org.ty.cloudCourse.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.dao.ClassDao;
import org.ty.cloudCourse.dao.PersonDao;
import org.ty.cloudCourse.dao.StudentDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.Point;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.StudentService;
import org.ty.cloudCourse.service.impl.CrudServiceImpl;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;
import org.ty.cloudCourse.util.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/11.
 */
public class StudentServiceImplTest extends BaseTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private StudentDao studentDao;

    @Test
    public void testAddStudent() {
        StudentInfo student = new StudentInfo();
        student.setUserName("stu4");
        student.setPassword("1234");
        student.setPointCount(0.0);
        student.setEnableStatus(0);
        crudService.insertOne(student, studentDao);
    }

    @Test
    public void testGetStudent() {
        AllExecution a = crudService.getOneById(new StudentInfo(4), studentDao);
        System.out.println(a.getStateInfo());
        System.out.println(a.getA());
    }

    @Test
    public void testLogin() {
        StudentInfo student = new StudentInfo();
        student.setUserName("stu2");
        student.setPassword("123");
        AllExecution execution = studentService.login(student);
        System.out.println(execution.getStateInfo());
        System.out.println(execution.getA());
    }

    @Test
    public void testStudentAddPoint() {
        Point point = new Point();
        point.setStudent(new StudentInfo(1));
        point.setCreateTime(sdf.format(new Date()));
        point.setPointReason("上课讲话");
        point.setPointNum(-1.0);
        studentService.studentAddPoint(point);
    }

    @Test
    public void testQueryCourseTeacherByStudent() {
        StudentInfo student = studentDao.getOneById(new StudentInfo(1));
        AllExecution execution = studentService.queryCourseTeacherByStudent(student);
        execution.getAList().forEach(System.out::println);
    }

    @Test
    public void testQueryHomeworkByStudentAndTime() {
        StudentInfo student = studentDao.getOneById(new StudentInfo(1));
        AllExecution execution = studentService.queryHomeworkByStudentAndTime(student, "2018-05-27");
        execution.getAList().forEach(System.out::println);
    }

    @Test
    public void testQueryClassGiftByStudent() {
        StudentInfo student = studentDao.getOneById(new StudentInfo(1));
        AllExecution execution = studentService.queryClassGiftByStudent(student);
        execution.getAList().forEach(System.out::println);
    }

}
