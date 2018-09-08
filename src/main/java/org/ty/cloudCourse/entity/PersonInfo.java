package org.ty.cloudCourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangtaiyang on 2018/5/11.
 */

/**
 * 用户信息（非学生）
 * id
 * 用户名
 * 密码
 * 用户类型 0 超管 1 教育机构 2 讲师
 * 真实姓名
 * 用户状态 0已启用 1已禁用
 * 用户头像路径
 * 课程集合
 * 班级集合
 * 父级用户
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo extends BaseEntity implements Comparator {
    private Integer id;
    private Integer userId;
    private String userName;
    private String password;
    private Integer userType;
    private String realName;
    private Integer enableStatus;
    private String userHeadImg;
    private List<Course> courseList;
    private List<Class> classList;
    private PersonInfo parentInfo;

    /**
     * 只有id时使用的构造器
     *
     * @param userId
     */
    public PersonInfo(Integer userId) {
        this.userId = this.id = userId;
    }

    public void setUserId(Integer userId) {
        this.userId = this.id = userId;
    }

    @Override
    public Map<String,BaseEntity> getParentEntity() {
        Map<String,BaseEntity> map = new HashMap<>();
        map.put("parentInfo",parentInfo);
        return map;
    }

    @Override
    public int compare(Object o1, Object o2) {
        PersonInfo p1 = (PersonInfo)o1;
        PersonInfo p2 = (PersonInfo)o2;
        return p1.getUserType().compareTo(p2.getUserType());
    }
}