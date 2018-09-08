package org.ty.cloudCourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangtaiyang on 2018/5/11.
 */

/**
 * 课程
 * id
 * 课程名
 * 课程描述
 * 封面地址
 * 课程价格
 * 最大学生数
 * 开始时间
 * 结束时间
 * 课程类型
 * 上该课程的班级集合
 * 负责该课程的教师集合
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {
    private Integer id;
    private Integer courseId;
    private String courseName;
    private String courseDesc;
    private String imgPath;
    private Double coursePrice;
    private Integer maxStudentCount;
    private String startTime;
    private String endTime;
    private CourseCategory courseCategory;
    private List<Class> clazzList = new ArrayList<>();
    private List<PersonInfo> teacherList = new ArrayList<>();

    public Course(Integer courseId) {
        this.courseId = this.id = courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = this.id = courseId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("courseCategory",courseCategory);
        return map;
    }
}