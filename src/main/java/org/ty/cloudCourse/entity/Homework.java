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
 * 学生作业
 * 作业Id
 * 作业简述
 * 创建时间
 * 一次作业最大积分增加量
 * 对应班级
 * 对应教师
 * 对应课程
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Homework extends BaseEntity {
    private Integer id;
    private Integer homeworkId;
    private String homeworkDesc;
    private String createTime;
    private Double maxPointAdd;
    private Class clazz;
    private PersonInfo teacher;
    private Course course;
 //   private Integer todayHWId;

    public Homework(Integer homeworkId) {
        this.homeworkId = this.id = homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = this.id = homeworkId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("clazz",clazz);
        map.put("teacher",teacher);
        map.put("course",course);
        return map;
    }
}