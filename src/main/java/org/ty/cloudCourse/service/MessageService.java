package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;

/**
 * @author kangtaiyang
 * @date 2018/6/23
 */
public interface MessageService extends BaseService {
    /**
     * 学生获取自己所有接受的消息
     *
     * @param addrer
     * @return
     */
    AllExecution queryMessageByAddrer(StudentInfo addrer);

    /**
     * 教师获取自己发送的所有消息
     *
     * @param teacher
     * @return
     */
    AllExecution queryMessageBySender(PersonInfo teacher);
}
