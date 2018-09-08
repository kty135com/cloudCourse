package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Class;

/**
 * Created by kangtaiyang on 2018/6/20.
 */
public interface ClassService extends BaseService {

    /**
     * 通过班级获取班级内所有学生
     *
     * @param clazz
     * @return
     */
    AllExecution queryStudentByClass(Class clazz);

    /**
     * 通过班级获取班级内所有教师
     *
     * @param clazz
     * @return
     */
    AllExecution queryTeacherByClass(Class clazz);

    /**
     * 通过班级获取班级内所有课程
     *
     * @param clazz
     * @return
     */
    AllExecution queryCourseByClass(Class clazz);
}
