package org.ty.cloudCourse.entity;

/**
 * Created by kangtaiyang on 2018/5/12.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 签到
 * id
 * 签到日期
 * 签到人数
 * 签到班级
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSign extends BaseEntity {
    private Integer signId;
    private String signDate;
    private Integer signCount;
    private Class clazz;
    private Course course;

    public ClassSign(Integer signId) {
        this.signId = signId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("clazz",clazz);
        map.put("course",course);
        return map;
    }
}