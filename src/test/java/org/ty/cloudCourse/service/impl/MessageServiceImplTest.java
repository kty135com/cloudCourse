package org.ty.cloudCourse.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ty.cloudCourse.BaseTest;
import org.ty.cloudCourse.dao.MessageDao;
import org.ty.cloudCourse.entity.Message;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.MessageService;

import static org.junit.Assert.*;

/**
 * @author kangtaiyang
 * @date 2018/7/7
 */
public class MessageServiceImplTest extends BaseTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private MessageDao messageDao;

    @Test
    public void queryMessageByAddrer() {
        messageService.queryMessageByAddrer(new StudentInfo(1)).getAList().forEach(System.out::println);
    }

    @Test
    public void queryMessageBySender() {
    }

    @Test
    public void getOneById() {
        System.out.println(crudService.getOneById(new Message(2),messageDao));
    }

    @Test
    public void updateOne() {
    }

    @Test
    public void updateOne1() {
    }

    @Test
    public void insertOne() {
    }

    @Test
    public void deleteOne() {
    }
}