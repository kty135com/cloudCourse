package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.StudentInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/12.
 */
public interface StudentDao extends BaseDao {
    /**
     * 用户登录
     *
     * @param student
     * @return
     */
    StudentInfo login(StudentInfo student);

    /**
     * 通过id获取用户
     *
     * @param student
     * @return
     */
    StudentInfo getOneById(StudentInfo student);

    /**
     * 插入用户
     *
     * @param student
     * @return
     */
    int insertOne(StudentInfo student);

    /**
     * 删除用户
     *
     * @param student
     * @return
     */
    int deleteOne(StudentInfo student);

    /**
     * 更新用户
     *
     * @param studentInfo
     * @return
     */
    int updateOne(StudentInfo studentInfo);

    /**
     * 列出班级里所有学生列表
     *
     * @param clazz
     * @return
     */
    List<StudentInfo> queryStudentByClass(Class clazz);

    /**
     * 获取所有学生
     *
     * @return
     */
    List<StudentInfo> queryStudent();
}
