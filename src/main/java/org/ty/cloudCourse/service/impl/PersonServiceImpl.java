package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.cloudCourse.dao.*;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.*;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.enums.UserStateEnum;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.PersonService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.MD5;
import org.ty.cloudCourse.util.RedisFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/10.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private CourseCategoryDao categoryDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassCourseTeacherDao cctDao;
    @Autowired
    private CrudService crudService;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil syncUtil;

    @Override
    public AllExecution login(PersonInfo person) {
        PersonInfo realperson;
        try {
            person.setPassword(MD5.md5(person.getPassword()));
            realperson = personDao.login(person);
            if (realperson == null) return new AllExecution(UserStateEnum.LOGIN_ERROR);
            if (realperson.getEnableStatus() == 1) return new AllExecution(UserStateEnum.OFFLINE);
        } catch (Exception e) {
            return new AllExecution(UserStateEnum.LOGIN_ERROR);
        }
        return success(realperson);
    }

    @Override
    public AllExecution querySchool() {
        List<PersonInfo> schoolList = null;
        try {
            schoolList = personDao.querySchool();
            Collections.sort(schoolList, new PersonInfo());
            System.out.println(schoolList);
        } catch (Exception e) {
            return innerError();
        }
        return success(schoolList);
    }

    @Override
    public AllExecution queryTeacherBySchool(PersonInfo school) {
        List<PersonInfo> teacherList;
        try {
            teacherList = personDao.queryTeacherBySchool(school);
        } catch (Exception e) {
            return innerError();
        }
        return success(teacherList);
    }

    @Override
    public AllExecution queryClassBySchool(PersonInfo school) {
        List<Class> classList;
        try {
            classList = redisFactory.queryAFromBid(Class.class, "school", school.getUserId());
            if (classList == null || classList.size() == 0) {
                classList = classDao.getClassBySchool(school);
            }
        } catch (Exception e) {
            return innerError();
        }
        return success(classList);
    }

    @Override
    public AllExecution queryCategoryBySchool(PersonInfo school) {
        List<CourseCategory> categoryList;
        try {
            categoryList = categoryDao.getCategoryBySchool(school);
        } catch (Exception e) {
            return innerError();
        }
        return success(categoryList);
    }

    @Override
    public AllExecution queryClassByTeacher(PersonInfo teacher) {
        List<Class> classList;
        try {
            classList = cctDao.queryClassByTeacher(teacher);
        } catch (Exception e) {
            return innerError();
        }
        return success(classList);
    }

    @Override
    public AllExecution queryCourseByTeacher(PersonInfo teacher) {
        List<Course> courseList;
        try {
            courseList = courseDao.queryCourseByTeacher(teacher);
        } catch (Exception e) {
            return innerError();
        }
        return success(courseList);
    }

    @Override
    public AllExecution queryStudentBySchool(PersonInfo school) {
        List<StudentInfo> studentList;
        try {
            List<Class> classList = classDao.getClassBySchool(school);
            studentList = new ArrayList<>();
            classList.forEach(x -> studentList.addAll(studentDao.queryStudentByClass(x)));
        } catch (Exception e) {
            return innerError();
        }

        return success(studentList);
    }

}
