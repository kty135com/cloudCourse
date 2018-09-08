package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ty.cloudCourse.controller.ClassCourseTeacherController;
import org.ty.cloudCourse.dao.*;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.enums.UserStateEnum;
import org.ty.cloudCourse.service.StudentService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.MD5;
import org.ty.cloudCourse.util.RedisFactory;
import org.ty.cloudCourse.util.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangtaiyang on 2018/6/11.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private PointDao pointDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ClassCourseTeacherDao cctDao;
    @Autowired
    private GiftDao giftDao;
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil syncUtil;

    @Override
    public AllExecution login(StudentInfo student) {
        StudentInfo realstudent;
        try {
            student.setPassword(MD5.md5(student.getPassword()));
            realstudent = studentDao.login(student);
            if (realstudent == null) return new AllExecution(UserStateEnum.LOGIN_ERROR);
            if (realstudent.getEnableStatus() == 1) return new AllExecution(UserStateEnum.OFFLINE);
        } catch (Exception e) {
            return innerError();
        }
        return success(realstudent);
    }


    @Override
    @Transactional
    public AllExecution studentAddPoint(Point point) {
        if (point.getStudent() == null) return nullId();
        pointDao.insertOne(point);
        StudentInfo student = studentDao.getOneById(point.getStudent());
        student.setPointCount(student.getPointCount() + point.getPointNum());
        point.setStudent(student);
        int i = pointDao.pointReackon(point);
        if (i != 0) {
            Map<String, String> map = new HashMap<>();
            map.put("pointCount", "" + student.getPointCount());
            Tuple t = syncUtil.getTypeAndKeyFromEntity(student);
            redisFactory.hmset(new Tuple("" + t.getB(), map));
        }
        return success(student);
    }

    @Override
    public AllExecution queryCourseTeacherByStudent(StudentInfo student) {
        List<Course> courseList;
        try {
            if (student.getClazz() == null) return nullId();
            Class cla = classDao.getOneById(student.getClazz());
            courseList = cctDao.queryCourseByClass(cla);
      //      courseList.forEach(x -> x.getTeacherList().addAll(cctDao.getTeacherByCc(new ClassCourseTeacher(new Class()))));
        } catch (Exception e) {
            return innerError();
        }
        return success(courseList);
    }

    @Override
    public AllExecution queryHomeworkByStudentAndTime(StudentInfo student, String date) {
        List<Homework> homeworkList;
        try {
            if (student.getClazz() == null) return nullId();
            homeworkList = homeworkDao.queryHomeworkListByClassAndCreateTime(new Tuple(student.getClazz(), date));
        } catch (Exception e) {
            return innerError();
        }
        return success(homeworkList);
    }

    @Override
    public AllExecution queryClassGiftByStudent(StudentInfo student) {
        List<Gift> classList;
        try {
            if (student.getClazz() == null) return nullId();
            classList = giftDao.getGiftListByClass(student.getClazz());
        } catch (Exception e) {
            return innerError();
        }
        return success(classList);
    }
}
