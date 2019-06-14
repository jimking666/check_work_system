package com.qushihan.check_work_system.student.api;

import java.util.List;

import com.qushihan.check_work_system.student.dto.StudentDetailRequest;
import com.qushihan.check_work_system.student.dto.StudentDto;

public interface StudentService {

    /**
     * 通过班级Id查询班中学生Dto列表
     *
     * @param clazzId
     * @return
     */
    List<StudentDto> getByClazzId(Long clazzId);

    /**
     * 通过班级id更改学生信息
     *
     * @param clazzId
     * @return
     */
    void updateByClazzId(Long clazzId);

    /**
     * 通过学生id列表查询StudentDto列表
     *
     * @param studentIds
     * @return
     */
    List<StudentDto> getByStudentIdList(List<Long> studentIds);

    /**
     * 通过搜索学生名称获取学生
     *
     * @return
     */
    List<StudentDto> getBySearchStudentName(String searchStudentName);
}
