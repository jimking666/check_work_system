package com.qushihan.check_work_system.teacher.api.impl;

import com.qushihan.check_work_system.inf.util.GetIdUtil;
import com.qushihan.check_work_system.teacher.api.TeacherRightService;
import com.qushihan.check_work_system.teacher.dao.TeacherRightDao;
import com.qushihan.check_work_system.teacher.dto.TeacherRightDto;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRight;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TeacherRightServiceImpl implements TeacherRightService {

    @Autowired
    private TeacherRightDao teacherRightDao;

    @Override
    public TeacherRightDto getByTeacherId(Long teacherId) {
        List<TeacherRight> teacherRights = teacherRightDao.getByTeacherId(teacherId);
        if (CollectionUtils.isEmpty(teacherRights)) {
            return new TeacherRightDto();
        }
        TeacherRight teacherRight = teacherRights.stream()
                .findFirst()
                .orElse(new TeacherRight());
        TeacherRightDto teacherRightDto = new TeacherRightDto();
        BeanUtils.copyProperties(teacherRight, teacherRightDto);
        return teacherRightDto;
    }

    @Override
    public int createTeacherRight(Long teacherId) {
        Long teacherRightId = GetIdUtil.getId();
        TeacherRight teacherRight = new TeacherRight();
        teacherRight.setTeacherRightId(teacherRightId);
        teacherRight.setTeacherId(teacherId);
        return teacherRightDao.createTeacherRight(teacherRight);
    }
}
