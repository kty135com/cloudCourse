package org.ty.cloudCourse.dao;

import org.ty.cloudCourse.entity.Course;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;

import java.util.List;

/**
 * Created by kangtaiyang on 2018/5/13.
 */
public interface CourseDao extends BaseDao {
    /**
     * 通过学校增加课程
     *
     * @param course
     * @return
     */
    int insertOne(Course course);

    /**
     * 更新课程
     *
     * @param course
     * @return
     */
    int updateOne(Course course);

    /**
     * 删除课程
     *
     * @param course
     * @return
     */
    int deleteOne(Course course);

    /**
     * 通过课程Id获取课程
     *
     * @param course
     * @return
     */
    Course getOneById(Course course);

    /**
     * 通过课程类型获取其全部课程
     *
     * @param category
     * @return
     */
    List<Course> queryCourseByCategory(CourseCategory category);

    List<Course> queryCourseByTeacher(PersonInfo teacher);

}
