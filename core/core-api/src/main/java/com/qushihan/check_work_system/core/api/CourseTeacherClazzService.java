package com.qushihan.check_work_system.core.api;

import java.util.List;

import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;

public interface CourseTeacherClazzService {

    /**
     * 创建课程教师班级
     *
     * @param courseId
     * @param teacherId
     * @param clazzId
     *
     * @return
     */
    String createCourseTeacherClazz(Long courseId, Long teacherId, Long clazzId);

    /**
     * 查询所有课程教师班级记录
     *
     * @return
     */
    List<CourseTeacherClazzDto> queryAllCourseTeacherClazz();

    /**
     * 删除课程教师班级
     *
     * @param courseTeacherClazzId
     *
     * @return
     */
    String deleteCourseTeacherClazz(Long courseTeacherClazzId);

    /**
     * 作业数量加1
     *
     * @return
     */
    Integer workCountAddOne(Long courseTeacherClazzId);
}
