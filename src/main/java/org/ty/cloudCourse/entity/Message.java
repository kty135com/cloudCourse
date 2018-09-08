package org.ty.cloudCourse.entity;

/**
 * Created by kangtaiyang on 2018/5/11.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息列表
 * id
 * 标题
 * 内容
 * 发送时间
 * 状态（0未发送 1已发送）
 * 发送者
 * 接受者
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {
    private Integer id;
    private Integer messageId;
    private String messageTitle;
    private String messageContent;
    private String sendTime;
    private Integer enableStatus;
    private PersonInfo sender;
    private StudentInfo addrer;

    public Message(Integer messageId) {
        this.messageId = this.id = messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = this.id = messageId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("sender",sender);
        map.put("addrer",addrer);
        return map;
    }
}