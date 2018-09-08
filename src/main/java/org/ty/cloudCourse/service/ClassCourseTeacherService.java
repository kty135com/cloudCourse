package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.ClassCourseTeacher;
import org.ty.cloudCourse.entity.PersonInfo;


/**
 * @author kangtaiyang
 * @date 2018/7/21
 */
public interface ClassCourseTeacherService extends BaseService {

    AllExecution addCc(ClassCourseTeacher cct);

    AllExecution delCc(ClassCourseTeacher cct);

    AllExecution setTeacherByCc(ClassCourseTeacher cct);

    AllExecution cctlist();

    AllExecution cctListBySchool(PersonInfo school);

    AllExecution queryCourseByClass(Class clazz);

    AllExecution getTeacherByCc(ClassCourseTeacher cct);
}
