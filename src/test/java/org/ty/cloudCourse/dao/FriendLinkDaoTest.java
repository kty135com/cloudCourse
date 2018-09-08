package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.FriendLink;

/**
 * Created by kangtaiyang on 2018/6/9.
 */
public class FriendLinkDaoTest extends BaseTest {

    @Autowired
    private FriendLinkDao friendLinkDao;

    @Test
    public void testInsertFriendLink() {
        FriendLink friendLink = new FriendLink();
        friendLink.setLinkName("4399小游戏");
        friendLink.setLinkUrl("www.4399.com");
        friendLink.setPriority(35);
        friendLink.setClickCount(0);
        friendLinkDao.insertOne(friendLink);
    }

    @Test
    public void testQueryFriendLink() {
        System.out.println(friendLinkDao.queryFriendLink());
    }

    @Test
    public void testGetFriendLinkById() {
        FriendLink friendLink = friendLinkDao.getOneById(new FriendLink(1));
        System.out.println(friendLink);
    }

    @Test
    public void testUpdateFriendLink() {
        FriendLink friendLink = friendLinkDao.getOneById(new FriendLink(1));
        friendLink.setLinkName("百度问答");
        friendLink.setLinkUrl("www.baidu.com/");
        friendLink.setPriority(40);
        friendLinkDao.updateOne(friendLink);
    }

    @Test
    public void testDeleteFriendLink() {
        FriendLink friendLink = friendLinkDao.getOneById(new FriendLink(1));
        friendLinkDao.deleteOne(friendLink);
    }
}
