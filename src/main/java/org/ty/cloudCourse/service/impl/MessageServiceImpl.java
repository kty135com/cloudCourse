package org.ty.cloudCourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.cloudCourse.dao.MessageDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Message;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.MessageService;
import org.ty.cloudCourse.util.DatabaseSyncUtil;
import org.ty.cloudCourse.util.RedisFactory;

import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/6/23
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private DatabaseSyncUtil syncUtil;

    @Override
    public AllExecution queryMessageByAddrer(StudentInfo addrer) {
        List<Message> messageList;
        try {
            messageList = redisFactory.queryAFromBid(Message.class, "addrer", addrer.getStudentId());
            if (messageList == null || messageList.size() == 0) {
                messageList = messageDao.queryMessageByAddrer(addrer);
            }
            return success(messageList);
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
    }

    @Override
    public AllExecution queryMessageBySender(PersonInfo teacher) {
        try {
            List<Message> messageList = messageDao.queryMessageBySender(teacher);
            return success(messageList);
        } catch (Exception e) {
            e.printStackTrace();
            return innerError();
        }
    }
}
