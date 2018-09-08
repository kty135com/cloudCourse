package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.cloudCourse.dao.ClassDao;
import org.ty.cloudCourse.dao.PersonDao;
import org.ty.cloudCourse.dao.StudentDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.service.ClassCourseTeacherService;
import org.ty.cloudCourse.service.ClassService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/20.
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private ClassCourseTeacherService cctService;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil syncUtil;

    @Override
    public AllExecution queryStudentByClass(Class clazz) {
        List<StudentInfo> studentList;
        try {
            if (clazz.getClassId() == null) return nullId();
            //      System.out.println("cla="+clazz);
            //      studentList = redisFactory.queryAFromBid(StudentInfo.class, "clazz", clazz.getClassId());
            //      System.out.println("studentList="+studentList);
            //      if (studentList==null||studentList.size()==0) {
            studentList = studentDao.queryStudentByClass(clazz);
            //      }
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
        return success(studentList);
    }

    @Override
    public AllExecution queryTeacherByClass(Class clazz) {
        List<PersonInfo> teacherList;
        try {
            if (clazz.getClassId() == null) return nullId();
            teacherList = classDao.queryTeacherByClass(clazz);
        } catch (Exception e) {
            return innerError();
        }
        return success(teacherList);
    }

    @Override
    public AllExecution queryCourseByClass(Class clazz) {
        List<Course> courseList;
        try {
            if (clazz.getClassId() == null) return nullId();
            courseList = cctService.queryCourseByClass(clazz).getAList();
        } catch (Exception e) {
            return innerError();
        }
        return success(courseList);
    }
}
