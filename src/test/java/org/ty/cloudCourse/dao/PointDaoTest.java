package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Point;
import org.ty.cloudCourse.entity.StudentInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kangtaiyang on 2018/5/11.
 */
public class PointDaoTest extends BaseTest {

    @Autowired
    private PointDao pointDao;

    @Test
    @Transactional
    public void testInsertPoint() {
        Point point = new Point();
        point.setPointNum(2.0);
        point.setPointReason("回答问题");
        point.setCreateTime(sdf.format(new Date()));
        point.setStudent(new StudentInfo(1));
        pointDao.insertOne(point);
        StudentInfo student = new StudentInfo();
        student.setPointCount(4.0);
        student.setStudentId(1);
        point.setStudent(student);
        System.out.println(point);
        pointDao.pointReackon(point);
    }
}
