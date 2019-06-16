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
     * 通过教师id得到课程教师班级Dto
     *
     * @return
     */
    List<CourseTeacherClazzDto> getByTeacherId(Long teacherId);

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

    /**
     * 通过课程id得到课程教师班级Dto
     *
     * @return
     */
    List<CourseTeacherClazzDto> getByCourseId(Long courseId);

    /**
     * 通过班级id得到课程教师班级Dto
     *
     * @return
     */
    List<CourseTeacherClazzDto> getByClazzId(Long clazzId);

    /**
     * 通过课程教师班级id获取课程教师班级记录
     *
     * @param courseTeacherClazzId
     * @return
     */
    CourseTeacherClazzDto getByCourseTeacherClazzId(Long courseTeacherClazzId);

    /**
     * 通过课程教师班级id修改课程教师班级记录
     *
     * @param courseTeacherClazzDto
     * @return
     */
    int updateByCourseTeacherClazzId(CourseTeacherClazzDto courseTeacherClazzDto);
}
