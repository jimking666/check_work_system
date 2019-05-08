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
    List<StudentDto> queryStudentDtoListByClazzId(Long clazzId);

    /**
     * 通过每个学生id批量更改学生信息
     * @param studentDtos
     * @return
     */
    Integer batchUpdateStudentByStudentId(List<StudentDto> studentDtos);

    /**
     * 通过学生id列表查询StudentDto列表
     * @param studentIds
     * @return
     */
    List<StudentDto> getByStudentIdList(List<Long> studentIds);
}
