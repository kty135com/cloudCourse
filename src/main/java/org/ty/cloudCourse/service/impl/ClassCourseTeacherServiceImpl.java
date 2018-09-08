package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.cloudCourse.dao.ClassCourseTeacherDao;
import org.ty.cloudCourse.dao.ClassDao;
import org.ty.cloudCourse.dao.PersonDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.ClassCourseTeacher;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.ClassCourseTeacherService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/7/21
 */
@Service
public class ClassCourseTeacherServiceImpl implements ClassCourseTeacherService {
    @Autowired
    private ClassCourseTeacherDao cctDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ClassDao classDao;

    @Override
    public AllExecution addCc(ClassCourseTeacher cct) {
        int i = 0;
        try {
            i = cctDao.addCc(cct);
        } catch (Exception e) {
            return innerError();
        }
        return i != 0 ? success(i) : innerError();
    }

    @Override
    public AllExecution delCc(ClassCourseTeacher cct) {
        int i = cctDao.delCc(cct);
        return i != 0 ? success(i) : innerError();
    }

    @Override
    public AllExecution setTeacherByCc(ClassCourseTeacher cct) {
        int i = cctDao.setTeacherByCc(cct);
        return i != 0 ? success(i) : innerError();
    }

    @Override
    public AllExecution cctlist() {
        List<ClassCourseTeacher> cctList = cctDao.cctlist();
        return success(cctList);
    }

    @Override
    public AllExecution cctListBySchool(PersonInfo school) {
        try {
            List<Class> classList = classDao.getClassBySchool(school);
            List<Integer> classIdList = new ArrayList<>();
            classList.forEach(x -> classIdList.add(x.getClassId()));
            List<ClassCourseTeacher> cctList = cctDao.cctlist();
            System.out.println(cctList);
            Iterator iter = cctList.iterator();
//            while (iter.hasNext()) {
//                ClassCourseTeacher cct = (ClassCourseTeacher) iter.next();
//                if (!classList.contains(cct.getClazz().getClassId())) {
//                    iter.remove();
//                }
//            }
            System.out.println("cctList=" + cctList);
            return success(cctList);
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
    }

    @Override
    public AllExecution queryCourseByClass(Class clazz) {
        try {
            List<Course> courseList = cctDao.queryCourseByClass(clazz);
            return success(courseList);
        } catch (Exception e) {
            return innerError();
        }
    }

    @Override
    public AllExecution getTeacherByCc(ClassCourseTeacher cct) {
        try {
            PersonInfo teacher = cctDao.getTeacherByCc(cct);
            return success(teacher);
        } catch (Exception e) {
            return innerError();
        }
    }
}
