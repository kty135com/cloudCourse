package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ty.cloudCourse.dao.*;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.BaseEntity;
import org.ty.cloudCourse.entity.ClassCourseTeacher;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;
import org.ty.cloudCourse.util.Tuple;

import java.util.Map;


/**
 * Created by kangtaiyang on 2018/6/10.
 */
@Deprecated
@Service
public class CrudServiceImpl implements CrudService {

    @Autowired
    private DatabaseSyncUtil databaseSyncUtil;

    @Autowired
    private RedisFactory redisFactory;

    @Override
    @Transactional
    public <A> AllExecution getOneById(A a, BaseDao dao) {
        Object obj;
        try {
            System.out.println("a="+a);
            System.out.println("rf="+redisFactory);
            Map<String, String> fv = redisFactory.hgetall(a);
            if (fv == null || fv.size() == 0) {
                obj = dao.getOneById(a);
                Tuple tuple = databaseSyncUtil.classTransforToHash((BaseEntity) obj);
                redisFactory.hmset(tuple);
                return success(obj);
            }
            obj = databaseSyncUtil.hashTransforToClass(fv, a.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
        return success(obj);
    }

    @Override
    @Transactional
    public <A, B> AllExecution updateOne(A a, B b, BaseDao dao) {
        Object obj = null;
        try {
            obj = dao.updateOne(new Tuple(a, b));
        } catch (Exception e) {
            return innerError();
        }
        return success(obj);
    }

    @Override
    @Transactional
    public <A> AllExecution updateOne(A a, BaseDao dao) {
        Object obj = null;
        try {
            obj = dao.updateOne(a);
            if ((int) obj != 0) {
                Tuple t = databaseSyncUtil.classTransforToHash((BaseEntity) a);
                redisFactory.hmset(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
        return success(obj);
    }

    @Override
    @Transactional
    public <A> AllExecution insertOne(A a, BaseDao dao) {
        Object obj = null;
        try {
            obj = dao.insertOne(a);
            if (a instanceof ClassCourseTeacher) return success(obj);
            if ((int) obj != 0) {

                Tuple t = databaseSyncUtil.classTransforToHash((BaseEntity) a);
                redisFactory.hmset(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
        return success(obj);
    }

    @Override
    @Transactional
    public <A> AllExecution deleteOne(A a, BaseDao dao) {
        Object obj = null;
        try {
            obj = dao.deleteOne(a);
            if (a instanceof ClassCourseTeacher) return success(obj);
            if ((int) obj != 0) {
                Tuple claKey = databaseSyncUtil.getTypeAndKeyFromEntity((BaseEntity) a);
                redisFactory.del(claKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
        return success(obj);
    }
}
