package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.ClassSign;
import org.ty.cloudCourse.entity.Course;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class SignDaoTest extends BaseTest {

    @Autowired
    private SignDao signDao;

    @Test
    public void testInsertSign() {
        ClassSign sign = new ClassSign();
        sign.setSignCount(20);
        sign.setSignDate(sdf.format(new Date()));
        sign.setClazz(new Class(1));
        sign.setCourse(new Course(1));
        signDao.insertOne(sign);
    }
}
