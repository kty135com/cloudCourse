package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/13.
 */
public interface ClassDao extends BaseDao {

    /**
     * 针对学校新建班级
     *
     * @param clazz
     * @return
     */
    int insertOne(Class clazz);

    /**
     * 通过班级id删除班级
     *
     * @param clazz
     * @return
     */
    int deleteOne(Class clazz);

    /**
     * 通过班级id更新班级
     *
     * @param clazz
     * @return
     */
    int updateOne(Class clazz);

    /**
     * 获取学校下所有班级
     *
     * @param school
     * @return
     */
    List<Class> getClassBySchool(PersonInfo school);

    /**
     * 获取班级中所有任课教师
     *
     * @param clazz
     * @return
     */
    List<PersonInfo> queryTeacherByClass(Class clazz);

    /**
     * 通过Id获取班级
     *
     * @param clazz
     * @return
     */
    Class getOneById(Class clazz);

    /**
     * 测试
     */
    List<Class> getCla(Class clazz);
}
