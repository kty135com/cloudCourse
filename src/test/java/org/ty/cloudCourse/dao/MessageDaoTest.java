package org.ty.cloudCourse.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.entity.Message;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;

import java.util.Date;

/**
 * Created by kangtaiyang on 2018/6/10.
 */
public class MessageDaoTest extends BaseTest {

    @Autowired
    private MessageDao messageDao;

    @Test
    public void testInsertMessage() {
        Message message = new Message();
        message.setAddrer(new StudentInfo(1));
        message.setEnableStatus(1);
        message.setSender(new PersonInfo(6));
        message.setSendTime(sdf.format(new Date()));
        message.setMessageTitle("我是第二条消息了");
        message.setMessageContent("没错！我又来啦！！！！！");
        messageDao.insertOne(message);
    }

    @Test
    public void testGetMessageById() {
        System.out.println(messageDao.getOneById(new Message(1)));
    }

    @Test
    public void testQueryMessageByAddrer() {
        System.out.println(messageDao.queryMessageByAddrer(new StudentInfo(1)));
    }

    @Test
    public void testQueryMessageBySender() {
        System.out.println(messageDao.queryMessageBySender(new PersonInfo(6)));
    }

    @Test
    public void testDeleteMessageById() {
        messageDao.deleteOne(new Message(2));
    }
}
