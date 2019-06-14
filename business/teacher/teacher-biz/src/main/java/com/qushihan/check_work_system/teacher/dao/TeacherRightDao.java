package com.qushihan.check_work_system.teacher.dao;

import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.teacher.mapper.auto.TeacherRightMapper;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRight;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRightDao {

    @Autowired
    private TeacherRightMapper teacherRightMapper;

    /**
     * 创建教师权限
     *
     * @param teacherRight
     * @return
     */
    public int createTeacherRight(TeacherRight teacherRight) {
        if (!Optional.ofNullable(teacherRight).isPresent()) {
            return 0;
        }
        return teacherRightMapper.insertSelective(teacherRight);
    }

    /**
     * 通过teacherId查询教师权益信息
     *
     * @param teacherId
     * @return
     */
    public List<TeacherRight> getByTeacherId(Long teacherId) {
        if (!Optional.ofNullable(teacherId).isPresent()) {
            return Collections.emptyList();
        }
        TeacherRightExample teacherRightExample = new TeacherRightExample();
        TeacherRightExample.Criteria criteria = teacherRightExample.createCriteria();
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        criteria.andTeacherIdEqualTo(teacherId);
        return teacherRightMapper.selectByExample(teacherRightExample);
    }
}
