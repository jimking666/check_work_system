package com.qushihan.check_work_system.teacher.api.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public String registerTeacher(RegisterTeacherRequest registerTeacherRequest) {
        Long teacherNumber = registerTeacherRequest.getTeacherNumber();
        // 检查是否重复注册
        List<Teacher> teachers = teacherDao.judgeRepeatRegister(teacherNumber);
        if (!CollectionUtils.isEmpty(teachers)) {
            return JudgeRegisterStatus.REPEAT_EXIST.getMessge();
        }
        // 注册成功
        Long teacherId = TeacherUtil.getTeacherId(teacherNumber);
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherNumber(teacherNumber);
        teacher.setTeacherPassword(registerTeacherRequest.getTeacherPassword());
        teacher.setTeacherName(registerTeacherRequest.getTeacherName());
        teacherDao.registerTeacher(teacher);
        return JudgeRegisterStatus.REGISTER_SUCCESS.getMessge();
    }

    @Override
    public TeacherDto loginTeacher(LoginTeacherRequest loginTeacherRequest) {
        List<Teacher> teachers = teacherDao.selectByTeacherNumberAndteacherPassword(
                loginTeacherRequest.getTeacherNumber(), loginTeacherRequest.getTeacherPassword());
        TeacherDto teacherDto = new TeacherDto();
        Teacher teacher = teachers.stream().findFirst().orElse(null);
        if (teacher != null) {
            BeanUtils.copyProperties(teacher, teacherDto);
            return teacherDto;
        };
        return null;
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
