package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.Homework;
import org.ty.cloudCourse.util.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangtaiyang on 2018/5/12.
 */
public interface HomeworkDao extends BaseDao {
    /**
     * 讲师通过班级`讲师`课程关系新建作业
     *
     * @param homework
     * @return
     */
    int insertOne(Homework homework);

    /**
     * 讲师通过班级`讲师`课程关系删除作业
     *
     * @param homework
     * @return
     */
    int deleteOne(Homework homework);

    /**
     * 讲师通过班级`讲师`课程关系修改作业
     *
     * @param homework
     * @return
     */
    int updateOne(Homework homework);

    /**
     * 通过班级和布置作业时间获取所有作业列表
     *
     * @param tuple
     * @return
     */
    List<Homework> queryHomeworkListByClassAndCreateTime(Tuple tuple);

    /**
     * 通过作业Id获取作业
     *
     * @param homework
     * @return
     */
    Homework getOneById(Homework homework);

    /**
     * 通过where获取Cct
     *
     * @param homework
     * @return
     */
    Homework getCctByWhere(Homework homework);
    /**
     * 通过where获取作业
     *
     * @param homework
     * @return
     */
    List<Homework> getHWByWhere(Homework homework);
}
