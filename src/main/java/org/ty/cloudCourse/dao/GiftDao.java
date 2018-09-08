package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Class;
import org.ty.cloudCourse.entity.Gift;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/13.
 */
public interface GiftDao extends BaseDao {
    /**
     * 根据班级新增礼品
     *
     * @param gift
     * @return
     */
    int insertOne(Gift gift);

    /**
     * 根据班级删除礼品
     *
     * @param gift
     * @return
     */
    int deleteOne(Gift gift);

    /**
     * 查询班级内所有礼品
     *
     * @param clazz
     * @return
     */
    List<Gift> getGiftListByClass(Class clazz);

    /**
     * 通过礼品的id获取礼品
     *
     * @param gift
     * @return
     */
    Gift getOneById(Gift gift);

    /**
     * 更新礼品信息
     *
     * @param gift
     * @return
     */
    int updateOne(Gift gift);

}
