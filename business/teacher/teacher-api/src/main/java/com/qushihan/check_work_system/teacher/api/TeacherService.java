package com.qushihan.check_work_system.teacher.api;

import com.qushihan.check_work_system.teacher.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    /**
     * 教师注册
     *
     * @param teacherNumber
     * @param teacherPassword
     * @param teacherName
     * @return
     */
    String registerTeacher(String teacherNumber, String teacherPassword, String teacherName);

    /**
     * 教师登陆
     *
     * @param teacherNumber
     * @param teacherPassword
     *
     * @return
     */
    List<TeacherDto> loginTeacher(String teacherNumber, String teacherPassword);

    /**
     * 通过教师id查询教师dto
     *
     * @param teacherId
     *
     * @return
     */
    TeacherDto queryTeacherDtoByTeacherId(Long teacherId);
}
