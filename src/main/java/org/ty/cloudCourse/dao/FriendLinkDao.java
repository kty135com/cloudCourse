package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.FriendLink;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/9.
 */
public interface FriendLinkDao extends BaseDao {

    /**
     * 新建一个友情链接
     *
     * @param friendLink
     * @return
     */
    int insertOne(FriendLink friendLink);

    /**
     * 获取所有友情链接
     *
     * @return
     */
    List<FriendLink> queryFriendLink();

    /**
     * 更新友情链接
     *
     * @param friendLink
     * @return
     */
    int updateOne(FriendLink friendLink);

    /**
     * 删除友情链接
     *
     * @param friendLink
     * @return
     */
    int deleteOne(FriendLink friendLink);

    /**
     * 通过链接Id获取链接详情
     *
     * @param friendLink
     * @return
     */
    FriendLink getOneById(FriendLink friendLink);
}
