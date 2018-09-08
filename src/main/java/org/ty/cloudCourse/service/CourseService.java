package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.CourseCategory;
import org.ty.cloudCourse.entity.PersonInfo;

/**
 * Created by kangtaiyang on 2018/6/14.
 */
public interface CourseService extends BaseService {

    /**
     * 通过课程类型获取所有课程
     *
     * @param category
     * @return
     */
    AllExecution queryCourseByCourseCategory(CourseCategory category);

}
