package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dao.BaseDao;
import org.ty.cloudCourse.dto.AllExecution;

/**
 * Created by kangtaiyang on 2018/6/14.
 */
public interface CrudService extends BaseService {

    /**
     * 通过Id获取某对象
     *
     * @param a
     * @param dao
     * @param <A>
     * @return
     */
    <A> AllExecution getOneById(A a, BaseDao dao);

    /**
     * 更新某对象
     *
     * @param a
     * @param dao
     * @param <A>
     * @return
     */
    <A> AllExecution updateOne(A a, BaseDao dao);

    /**
     * 更新某对象
     *
     * @param a
     * @param b
     * @param dao
     * @param <A>
     * @param <B>
     * @return
     */
    <A, B> AllExecution updateOne(A a, B b, BaseDao dao);

    /**
     * 插入某对象
     *
     * @param a
     * @param dao
     * @param <A>
     * @return
     */
    <A> AllExecution insertOne(A a, BaseDao dao);

    /**
     * 删除某对象
     *
     * @param a
     * @param dao
     * @param <A>
     * @return
     */
    <A> AllExecution deleteOne(A a, BaseDao dao);
}
