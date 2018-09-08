package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Message;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/6/10.
 */
public interface MessageDao extends BaseDao {
    /**
     * 新建信息
     *
     * @param message
     * @return
     */
    int insertOne(Message message);

    /**
     * 通过Id获得信息
     *
     * @param message
     * @return
     */
    Message getOneById(Message message);

    /**
     * 学生获取自己收到的所有信息
     *
     * @param addrer
     * @return
     */
    List<Message> queryMessageByAddrer(StudentInfo addrer);

    /**
     * 讲师查看自己发送的所有信息
     *
     * @param sender
     * @return
     */
    List<Message> queryMessageBySender(PersonInfo sender);

    /**
     * 通过Id删除信息
     *
     * @param message
     * @return
     */
    int deleteOne(Message message);
}
