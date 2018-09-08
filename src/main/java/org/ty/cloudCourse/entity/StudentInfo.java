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
 * 学生信息
 * id
 * 用户名
 * 密码
 * 真实姓名
 * 年龄
 * 出生日期
 * 性别
 * 用户状态 0已启用 1已禁用
 * 联系电话
 * 头像路径
 * 积分总数
 * 所在班级
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo extends BaseEntity {
    private Integer id;
    private Integer studentId;
    private String userName;
    private String password;
    private String realName;
    private Integer age;
    private String birth;
    private String sex;
    private Integer enableStatus;
    private String tel;
    private String userHeadImg;
    private Double pointCount;
    private Class clazz;
    private PersonInfo school;

    public StudentInfo(Integer studentId) {
        this.studentId =this.id= studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = this.id = studentId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("clazz",clazz);
        return map;
    }
}