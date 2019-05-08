package com.qushihan.check_work_system.student.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qushihan.check_work_system.student.model.auto.Student;

public interface StudentBizMapper {

    /**
     * 通过学生id批量修改学生表
     *
     * @param studentList
     *
     * @return
     */
    int batchUpdateStudentByStudentId(@Param("itemList") List<Student> studentList);

}
