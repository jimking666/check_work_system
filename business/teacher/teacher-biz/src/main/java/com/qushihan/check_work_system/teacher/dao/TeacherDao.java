package com.qushihan.check_work_system.teacher.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.qushihan.check_work_system.teacher.mapper.auto.TeacherRightMapper;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRight;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRightExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.teacher.mapper.auto.TeacherMapper;
import com.qushihan.check_work_system.teacher.model.auto.Teacher;
import com.qushihan.check_work_system.teacher.model.auto.TeacherExample;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Repository
public class TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 创建教师信息
     *
     * @param teacher
     *
     * @return
     */
    public int createTeacher(Teacher teacher) {
        if (!Optional.ofNullable(teacher).isPresent()) {
            return 0;
        }
        return teacherMapper.insertSelective(teacher);
    }

    /**
     * 教师登陆
     *
     * @param teacherNumber
     * @param teacherPassword
     *
     * @return
     */
    public List<Teacher> getByTeacherNumberAndTeacherPassword(String teacherNumber, String teacherPassword) {
        if (StringUtils.isEmpty(teacherNumber)) {
            return Collections.emptyList();
        }
        if (StringUtils.isEmpty(teacherPassword)) {
            return Collections.emptyList();
        }
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherNumberEqualTo(teacherNumber);
        criteria.andTeacherPasswordEqualTo(teacherPassword);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return teacherMapper.selectByExample(teacherExample);
    }

    /**
     * 检查是否重复注册
     *
     * @param teacherNumber
     *
     * @return
     */
    public List<Teacher> judgeRepeatRegister(String teacherNumber) {
        if (StringUtils.isEmpty(teacherNumber)) {
            return Collections.emptyList();
        }
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherNumberEqualTo(teacherNumber);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return teacherMapper.selectByExample(teacherExample);
    }

    /**
     * 根据教师id查询教师列表
     *
     * @param teacherId
     *
     * @return
     */
    public List<Teacher> queryTeacherListByTeacherId(Long teacherId) {
        if (!Optional.ofNullable(teacherId).isPresent()) {
            return Collections.EMPTY_LIST;
        }
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return teacherMapper.selectByExample(teacherExample);
    }
}
