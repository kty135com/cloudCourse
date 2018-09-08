package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/12.
 */
public interface PersonDao extends BaseDao {
    /**
     * 用户登录
     *
     * @param person
     * @return
     */
    PersonInfo login(PersonInfo person);

    /**
     * 通过id获取用户
     *
     * @param person
     * @return
     */
    PersonInfo getOneById(PersonInfo person);

    /**
     * 插入用户
     *
     * @param person
     * @return
     */
    int insertOne(PersonInfo person);

    /**
     * 删除用户
     *
     * @param person
     * @return
     */
    int deleteOne(PersonInfo person);

    /**
     * 更新用户
     *
     * @param personInfo
     * @return
     */
    int updateOne(PersonInfo personInfo);

    /**
     * 获取培训机构列表
     *
     * @return
     */
    List<PersonInfo> querySchool();

    /**
     * 获取该培训机构下所有讲师列表
     *
     * @param school
     * @return
     */
    List<PersonInfo> queryTeacherBySchool(PersonInfo school);

    /**
     * 通过用户类型获取用户
     *
     * @param userType
     * @return
     */
    List<PersonInfo> queryPersonByType(int userType);
}
