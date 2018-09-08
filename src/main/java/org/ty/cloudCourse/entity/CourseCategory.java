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
 * 课程类别
 * id
 * 类别名
 * 类别描述
 * 权重
 * 创建时间
 * 最后修改时间
 * 所属学校
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategory extends BaseEntity {
    private Integer id;
    private Integer courseCategoryId;
    private String categoryName;
    private String categoryDesc;
    private Integer priority;
    private String createTime;
    private String lastEditTime;
    private PersonInfo school;

    public CourseCategory(Integer categoryId) {
        this.courseCategoryId = this.id = categoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = this.id = courseCategoryId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("school",school);
        return map;
    }
}