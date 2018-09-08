package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/13.
 */
public interface CourseCategoryDao extends BaseDao {

    /**
     * 新建课程类型
     *
     * @param category
     * @return
     */
    int insertOne(CourseCategory category);

    /**
     * 删除课程类型
     *
     * @param category
     * @return
     */
    int deleteOne(CourseCategory category);

    /**
     * 通过课程类型的Id获取课程类型
     *
     * @param category
     * @return
     */
    CourseCategory getOneById(CourseCategory category);

    /**
     * 通过学校Id获取该学校下课程类型
     *
     * @param school
     * @return
     */
    List<CourseCategory> getCategoryBySchool(PersonInfo school);

    /**
     * 更新课程类型
     *
     * @param category
     * @return
     */
    int updateOne(CourseCategory category);
}
