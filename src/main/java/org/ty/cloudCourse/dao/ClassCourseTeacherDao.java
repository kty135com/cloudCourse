package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.ClassCourseTeacher;
import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/7/21
 */
public interface ClassCourseTeacherDao {

    int addCc(ClassCourseTeacher cct);

    int delCc(ClassCourseTeacher cct);

    int setTeacherByCc(ClassCourseTeacher cct);

    List<ClassCourseTeacher> cctlist();

    List<Course> queryCourseByClass(Class clazz);

    PersonInfo getTeacherByCc(ClassCourseTeacher cct);

    List<Class> queryClassByTeacher(PersonInfo teacher);

    List<ClassCourseTeacher> getCcByTeacher(PersonInfo teacher);
}
