package org.ty.cloudCourse.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangtaiyang on 2018/5/11.
 */

/**
 * 班级
 * id
 * 学生总数
 * 包含课程集合
 * 包含讲师集合
 * 所属学校
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class extends BaseEntity{
    private Integer id;
    private Integer classId;
    private String className;
    private Integer studentCount;
    private List<Course> courseList;
    private List<PersonInfo> teacherList;
    private List<StudentInfo> studentList;
    private PersonInfo school;

    public Class(Integer classId) {
        this.classId = this.id = classId;
    }

    public void setClassId(Integer classId) {
        this.classId = this.id = classId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("school",school);
        return map;
    }

}
