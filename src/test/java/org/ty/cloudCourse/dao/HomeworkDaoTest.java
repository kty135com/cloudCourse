package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.util.Tuple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class HomeworkDaoTest extends BaseTest {

    @Autowired
    private HomeworkDao homeworkDao;

    @Test
    public void testInsertHomework() {
        Homework homework = new Homework();
        homework.setClazz(new Class(1));
        homework.setCourse(new Course(1));
        homework.setCreateTime(sdf.format(new Date()));
        homework.setHomeworkDesc("这是" + sdf.format(new Date()) + "  的作业2");
        homework.setMaxPointAdd(2.0);
        homework.setTeacher(new PersonInfo(6));
        homeworkDao.insertOne(homework);
    }

    @Test
    public void testDeleteHomework() {
        homeworkDao.deleteOne(new Homework(2));
    }

    @Test
    public void testUpdateHomework() {
        Homework homework = new Homework();
        homework.setHomeworkId(1);
        homework.setMaxPointAdd(4.0);
        homework.setHomeworkDesc("我是新的描述");
        homeworkDao.updateOne(homework);
    }

    @Test
    public void testGetHomeworkById() {
        Homework homework = homeworkDao.getOneById(new Homework(1));
        System.out.println(homework);
    }

    @Test
    public void testGetHomeworkListByClassAndCreateTime() {
        Tuple tuple = new Tuple(new Class(1), "2018-05-27");
        homeworkDao.queryHomeworkListByClassAndCreateTime(tuple).forEach(System.out::println);
    }

    @Test
    public void testGetOneByWhere() {
        Homework hw = new Homework();
        hw.setClazz(new Class(1));
        hw.setCourse(new Course(1));
        hw.setTeacher(new PersonInfo(6));
        System.out.println(homeworkDao.getHWByWhere(hw));
    }
}
