package org.ty.cloudCourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kangtaiyang on 2018/5/12.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassCourseTeacher {
    private Class clazz;
    private Course course;
    private PersonInfo teacher;
    private String createTime;
    private String editTime;

    public ClassCourseTeacher(Class clazz, Course course, String createTime) {
        this.clazz = clazz;
        this.course = course;
        this.createTime = createTime;
    }

    public ClassCourseTeacher(Class clazz, Course course) {
        this.clazz = clazz;
        this.course = course;
    }

    public ClassCourseTeacher(Class clazz, Course course, PersonInfo teacher, String editTime) {
        this.clazz = clazz;
        this.course = course;
        this.teacher = teacher;
        this.editTime = editTime;
    }
}
