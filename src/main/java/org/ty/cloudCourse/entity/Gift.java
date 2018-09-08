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
 * 礼品对象
 * 礼品Id
 * 礼品描述
 * 权重
 * 礼物总数
 * 礼品剩余个数
 * 创建时间
 * 对应班级
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gift extends BaseEntity {
    private Integer id;
    private Integer giftId;
    private String giftName;
    private String giftDesc;
    private Integer priority;
    private Integer giftCount;
    private Integer giftNum;
    private String createTime;
    private Class clazz;
    private Double giftPoint;

    public Gift(Integer giftId) {
        this.giftId = this.id = giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = this.id = giftId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("clazz",clazz);
        return map;
    }
}