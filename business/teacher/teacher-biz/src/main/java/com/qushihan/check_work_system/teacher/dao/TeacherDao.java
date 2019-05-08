package com.qushihan.check_work_system.teacher.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.teacher.mapper.auto.TeacherMapper;
import com.qushihan.check_work_system.teacher.model.auto.Teacher;
import com.qushihan.check_work_system.teacher.model.auto.TeacherExample;

@Repository
public class TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 教师注册
     *
     * @param teacher
     *
     * @return
     */
    public Integer registerTeacher(Teacher teacher) {
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
    public List<Teacher> selectByTeacherNumberAndteacherPassword(Long teacherNumber, String teacherPassword) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherNumberEqualTo(teacherNumber);
        criteria.andTeacherPasswordEqualTo(teacherPassword);
        return teacherMapper.selectByExample(teacherExample);
    }

    /**
     * 检查是否重复注册
     *
     * @param teacherNumber
     *
     * @return
     */
    public List<Teacher> judgeRepeatRegister(Long teacherNumber) {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherNumberEqualTo(teacherNumber);
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
