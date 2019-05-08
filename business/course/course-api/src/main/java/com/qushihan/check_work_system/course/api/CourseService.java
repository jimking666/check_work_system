package com.qushihan.check_work_system.course.api;

import java.util.List;

import com.qushihan.check_work_system.course.dto.CourseDto;

public interface CourseService {

    /**
     * 创建课程
     *
     * @param courseName
     *
     * @return
     */
    String createCourse(String courseName);

    /**
     * 查询所有课程
     *
     * @return
     */
    List<CourseDto> queryAllCourse();

    /**
     * 删除课程
     *
     * @param courseId
     *
     * @return
     */
    String deleteCourse(Long courseId);

    /**
     * 通过课程id查询课程dto
     *
     * @param courseId
     *
     * @return
     */
    CourseDto queryCourseDtoByCourseId(Long courseId);
}
