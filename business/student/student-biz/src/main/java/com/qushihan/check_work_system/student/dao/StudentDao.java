package com.qushihan.check_work_system.student.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.student.mapper.auto.StudentMapper;
import com.qushihan.check_work_system.student.mapper.biz.StudentBizMapper;
import com.qushihan.check_work_system.student.model.auto.Student;
import com.qushihan.check_work_system.student.model.auto.StudentExample;

@Repository
public class StudentDao {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentBizMapper studentBizMapper;

    /**
     * 通过班级id查询学生
     *
     * @param clazzId
     *
     * @return
     */
    public List<Student> queryStudentListByClazzId(Long clazzId) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andClazzIdEqualTo(clazzId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return studentMapper.selectByExample(studentExample);
    }

    /**
     * 通过每个学生id批量更新学生信息
     *
     * @param students
     *
     * @return
     */
    public Integer batchUpdateStudentByStudentId(List<Student> students) {
        return studentBizMapper.batchUpdateStudentByStudentId(students);
    }

    /**
     * 通过学生id列表查询Student列表
     *
     * @param studentIds
     *
     * @return
     */
    public List<Student> getByStudentIdList(List<Long> studentIds) {
        if (CollectionUtils.isEmpty(studentIds)) {
            return Collections.EMPTY_LIST;
        }
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentIdIn(studentIds);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return studentMapper.selectByExample(studentExample);
    }
}
