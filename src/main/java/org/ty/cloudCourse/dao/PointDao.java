package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Point;
import org.ty.cloudCourse.entity.StudentInfo;

/**
 * Created by kangtaiyang on 2018/5/12.
 */
public interface PointDao extends BaseDao {

    /**
     * 增加积分
     *
     * @param point
     * @return
     */
    int insertOne(Point point);

    /**
     * 更新对应学生积分
     *
     * @param point
     * @return
     */
    int pointReackon(Point point);
}
