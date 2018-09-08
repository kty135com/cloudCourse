package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.util.Tuple;

/**
 * Created by kangtaiyang on 2018/6/13.
 */
public  interface BaseDao<T,N> {

    int insertOne(T t);

    int deleteOne(T t);

    int updateOne(T t);

    int updateOne(Tuple tuple);

    T getOneById(T t);
}
