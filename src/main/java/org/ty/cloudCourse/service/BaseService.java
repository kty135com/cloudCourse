package org.ty.cloudCourse.service;

import org.springframework.ui.Model;
import org.ty.cloudCourse.dao.BaseDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.enums.UserStateEnum;
import org.ty.cloudCourse.util.MD5;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/19.
 */
public interface BaseService {

    /**
     * 集合操作成功时使用的构造
     *
     * @param aList
     * @param <A>
     * @return
     */
    default <A> AllExecution success(List<A> aList) {
        AllExecution execution = new AllExecution(aList, UserStateEnum.SUCCESS);
        execution.setCount(aList.size());
        return execution;
    }

    /**
     * 单体操作成功时使用的构造
     *
     * @param a
     * @param <A>
     * @return
     */
    default <A> AllExecution success(A a) {
        return new AllExecution(a, UserStateEnum.SUCCESS);
    }

    /**
     * 内部系统错误
     *
     * @return
     */
    default AllExecution innerError() {
        return new AllExecution(UserStateEnum.INNER_ERROR);
    }

    /**
     * Id为空
     *
     * @return
     */
    default AllExecution nullId() {
        return new AllExecution(UserStateEnum.NULL_ID);
    }

    /**
     * 修改密码
     *
     * @param a
     * @param dao
     * @param <A>
     * @return
     */
    default <A> AllExecution changePassword(A a, BaseDao dao) {
        try {
            if (a instanceof PersonInfo) ((PersonInfo) a).setPassword(MD5.md5(((PersonInfo) a).getPassword()));
            if (a instanceof StudentInfo) ((StudentInfo) a).setPassword(MD5.md5(((StudentInfo) a).getPassword()));
            dao.updateOne(a);
        } catch (Exception e) {
            return innerError();
        }
        return success(a);
    }

}
