package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.impl.PersonServiceImpl;

/**
 * Created by kangtaiyang on 2018/5/17.
 */
public interface PersonService extends BaseService {

    /**
     * 登陆
     *
     * @param person
     * @return
     */
    AllExecution login(PersonInfo person);

    /* ----------------- 超管操作 ----------------- */

    /**
     * 获取所有学校
     *
     * @return
     */
    AllExecution querySchool();

    /* ----------------- 学校操作 ----------------- */

    /**
     * 根据学校获取校内讲师
     *
     * @param school
     * @return
     */
    AllExecution queryTeacherBySchool(PersonInfo school);

    /**
     * 根据学校获取校内所有班级
     *
     * @param school
     * @return
     */
    AllExecution queryClassBySchool(PersonInfo school);

    /**
     * 根据学校获取其下所有课程类型
     *
     * @param school
     * @return
     */
    AllExecution queryCategoryBySchool(PersonInfo school);

    /**
     * 根据学校获取校内所有学生
     *
     * @param school
     * @return
     */
    AllExecution queryStudentBySchool(PersonInfo school);

    /* ----------------- 教师操作 ----------------- */

    /**
     * 根据教师获取其下所有班级
     *
     * @param teacher
     * @return
     */
    AllExecution queryClassByTeacher(PersonInfo teacher);

    /**
     * 根据教师获取其教的所有课程
     *
     * @param teacher
     * @return
     */
    AllExecution queryCourseByTeacher(PersonInfo teacher);
}
