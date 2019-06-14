package com.qushihan.check_work_system.teacher.api;

import com.qushihan.check_work_system.teacher.dto.TeacherRightDto;

public interface TeacherRightService {


    /**
     * 通过教师id查询教师权益信息
     *
     * @param teacherId
     *
     * @return
     */
    TeacherRightDto getByTeacherId(Long teacherId);

    /**
     * 创建教师权益信息
     *
     * @param teacherId
     * @return
     */
    int createTeacherRight(Long teacherId);
}
