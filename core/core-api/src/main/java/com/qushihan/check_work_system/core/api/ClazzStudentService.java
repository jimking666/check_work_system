package com.qushihan.check_work_system.core.api;

import com.qushihan.check_work_system.core.dto.ClazzStudentDto;

import java.util.List;

public interface ClazzStudentService {

    /**
     * 通过班级学生id更改ClazzStudent记录
     *
     * @param clazzStudentDto
     *
     * @return
     */
    int updateByClazzStudentId(ClazzStudentDto clazzStudentDto);

    /**
     * 通过班级id查询ClazzStudent记录
     * @param clazzId
     * @return
     */
    List<ClazzStudentDto> getByClazzId(Long clazzId);
}
