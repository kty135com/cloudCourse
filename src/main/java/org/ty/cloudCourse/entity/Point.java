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
 * 积分对象
 * 积分Id
 * 加减分原因
 * 加减分数量
 * 创建时间
 * 对应学生
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point extends BaseEntity {
    private Integer id;
    private Integer pointId;
    private String pointReason;
    private Double pointNum;
    private String createTime;
    private StudentInfo student;

    public Point(Integer pointId) {
        this.pointId = this.id = pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = this.id = pointId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("student",student);
        return map;
    }
}