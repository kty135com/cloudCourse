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
 * 友情链接
 * id
 * 链接名称
 * 链接地址
 * 权重
 * 点击次数
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendLink extends BaseEntity {
    private Integer id;
    private Integer linkId;
    private String linkName;
    private String linkUrl;
    private Integer priority;
    private Integer clickCount;

    public FriendLink(Integer linkId) {
        this.linkId = this.id = linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = this.id = linkId;
    }

    @Override
    public Map<String, BaseEntity> getParentEntity() {
        return new HashMap<>();
    }
}