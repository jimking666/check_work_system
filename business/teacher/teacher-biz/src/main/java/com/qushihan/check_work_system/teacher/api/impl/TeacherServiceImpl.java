package com.qushihan.check_work_system.teacher.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.qushihan.check_work_system.inf.util.GetIdUtil;
import com.qushihan.check_work_system.teacher.api.TeacherRightService;
import com.qushihan.check_work_system.teacher.dao.TeacherRightDao;
import com.qushihan.check_work_system.teacher.dto.TeacherRightDto;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRight;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.teacher.api.TeacherService;
import com.qushihan.check_work_system.teacher.dao.TeacherDao;
import com.qushihan.check_work_system.teacher.dto.LoginTeacherRequest;
import com.qushihan.check_work_system.teacher.dto.RegisterTeacherRequest;
import com.qushihan.check_work_system.teacher.dto.TeacherDto;
import com.qushihan.check_work_system.teacher.enums.JudgeRegisterStatus;
import com.qushihan.check_work_system.teacher.model.auto.Teacher;
import com.qushihan.check_work_system.teacher.util.TeacherUtil;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherRightService teacherRightService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String registerTeacher(String teacherNumber, String teacherPassword, String teacherName) {
        // 检查是否重复注册
        List<Teacher> teachers = teacherDao.judgeRepeatRegister(teacherNumber);
        if (!CollectionUtils.isEmpty(teachers)) {
            return JudgeRegisterStatus.REPEAT_EXIST.getMessge();
        }
        // 教师信息创建
        Long teacherId = GetIdUtil.getId();
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherNumber(teacherNumber);
        teacher.setTeacherPassword(teacherPassword);
        teacher.setTeacherName(teacherName);
        teacherDao.createTeacher(teacher);
        // 教师权限创建
        teacherRightService.createTeacherRight(teacherId);
        return JudgeRegisterStatus.REGISTER_SUCCESS.getMessge();
    }

    @Override
    public List<TeacherDto> loginTeacher(String teacherNumber, String teacherPassword) {
        List<Teacher> teachers = teacherDao.getByTeacherNumberAndTeacherPassword(
                teacherNumber, teacherPassword);
        if (CollectionUtils.isEmpty(teachers)) {
            return Collections.emptyList();
        }
        return teachers.stream()
                .map(teacher -> {
                    TeacherDto teacherDto = new TeacherDto();
                    BeanUtils.copyProperties(teacher, teacherDto);
                    return teacherDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto queryTeacherDtoByTeacherId(Long teacherId) {
        List<Teacher> teachers = teacherDao.queryTeacherListByTeacherId(teacherId);
        Teacher teacher = teachers.stream().findFirst().orElse(null);
        if (Optional.ofNullable(teacher).isPresent()) {
            TeacherDto teacherDto = new TeacherDto();
            BeanUtils.copyProperties(teacher, teacherDto);
            return teacherDto;
        }
        return new TeacherDto();
    }
}
