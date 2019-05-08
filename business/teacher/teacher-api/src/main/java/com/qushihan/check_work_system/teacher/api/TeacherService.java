package com.qushihan.check_work_system.teacher.api;

import com.qushihan.check_work_system.teacher.dto.LoginTeacherRequest;
import com.qushihan.check_work_system.teacher.dto.RegisterTeacherRequest;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;

public interface TeacherService {

    /**
     * 教师注册
     *
     * @param registerTeacherRequest
     *
     * @return
     */
    String registerTeacher(RegisterTeacherRequest registerTeacherRequest);

    /**
     * 教师登陆
     *
     * @param loginTeacherRequest
     *
     * @return
     */
    TeacherDto loginTeacher(LoginTeacherRequest loginTeacherRequest);

    /**
     * 通过教师id查询教师dto
     *
     * @param teacherId
     *
     * @return
     */
    TeacherDto queryTeacherDtoByTeacherId(Long teacherId);
}
