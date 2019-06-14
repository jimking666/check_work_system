package com.qushihan.check_work_system.core.api.impl;

import com.qushihan.check_work_system.core.dto.ClazzStudentDto;
import com.qushihan.check_work_system.core.model.auto.ClazzStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qushihan.check_work_system.core.api.ClazzStudentService;
import com.qushihan.check_work_system.core.dao.ClazzStudentDao;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClazzStudentServiceImpl implements ClazzStudentService {

    @Autowired
    private ClazzStudentDao clazzStudentDao;

    @Override
    public int updateByClazzStudentId(ClazzStudentDto clazzStudentDto) {
        ClazzStudent clazzStudent = new ClazzStudent();
        BeanUtils.copyProperties(clazzStudentDto, clazzStudent);
        return clazzStudentDao.updateByClazzStudentId(clazzStudent);
    }

    @Override
    public List<ClazzStudentDto> getByClazzId(Long clazzId) {
        List<ClazzStudent> clazzStudents = clazzStudentDao.getByClazzId(clazzId);
        if (CollectionUtils.isEmpty(clazzStudents)) {
            return Collections.emptyList();
        }
        return clazzStudents.stream()
                .map(clazzStudent -> {
                    ClazzStudentDto clazzStudentDto = new ClazzStudentDto();
                    BeanUtils.copyProperties(clazzStudent, clazzStudentDto);
                    return clazzStudentDto;
                }).collect(Collectors.toList());
    }
}
