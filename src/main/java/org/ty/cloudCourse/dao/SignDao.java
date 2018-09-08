package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.ClassSign;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/12.
 */
public interface SignDao extends BaseDao {
    int insertOne(ClassSign sign);

    ClassSign getOneById(ClassSign sign);

    List<ClassSign> getCsByWhere(ClassSign cs);
}
