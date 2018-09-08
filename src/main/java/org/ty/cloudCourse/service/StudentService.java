package org.ty.cloudCourse.service;

import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Point;
import org.ty.cloudCourse.entity.StudentInfo;

/**
 * Created by kangtaiyang on 2018/5/17.
 */
public interface StudentService extends BaseService {
    /**
     * 学生登陆
     *
     * @param student
     * @return
     */
    AllExecution login(StudentInfo student);


    /**
     * 学生加分
     *
     * @param point
     * @return
     */
    AllExecution studentAddPoint(Point point);


    /**
     * 根据学生获取所上的所有课及上课老师
     *
     * @param student
     * @return
     */
    AllExecution queryCourseTeacherByStudent(StudentInfo student);

    /**
     * 通过学生获取该日期下布置的作业集合
     *
     * @param student
     * @param date
     * @return
     */
    AllExecution queryHomeworkByStudentAndTime(StudentInfo student, String date);

    /**
     * 学生查询班级所有礼物
     *
     * @param student
     * @return
     */
    AllExecution queryClassGiftByStudent(StudentInfo student);
}
