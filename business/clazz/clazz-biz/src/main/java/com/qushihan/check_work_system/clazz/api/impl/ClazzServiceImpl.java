package com.qushihan.check_work_system.clazz.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.core.dto.ClazzStudentDto;
import com.qushihan.check_work_system.core.dto.CourseTeacherClazzDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.clazz.api.ClazzService;
import com.qushihan.check_work_system.clazz.dao.ClazzDao;
import com.qushihan.check_work_system.clazz.dto.ClazzDto;
import com.qushihan.check_work_system.clazz.enums.CreateClazzStatus;
import com.qushihan.check_work_system.clazz.enums.DeleteClazzStatus;
import com.qushihan.check_work_system.clazz.model.auto.Clazz;
import com.qushihan.check_work_system.clazz.util.ClazzUtil;
import com.qushihan.check_work_system.core.api.ClazzStudentService;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.student.api.StudentService;
import com.qushihan.check_work_system.student.dto.StudentDto;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzStudentService clazzStudentService;

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;

    @Override
    public String createClazz(String clazzName) {
        // 先查重复
        if (!CollectionUtils.isEmpty(clazzDao.queryClazzByClazzName(clazzName))) {
            return CreateClazzStatus.REPEAT_CREATE_FAIL.getMessage();
        }
        // 若不重复执行插入
        Long clazzId = ClazzUtil.getClazzId();
        Clazz clazz = new Clazz();
        clazz.setClazzName(clazzName);
        clazz.setClazzId(clazzId);
        clazzDao.createClazz(clazz);
        return CreateClazzStatus.CREATE_SUCCESS.getMessage();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ClazzDto> queryAllClazz() {
        List<Clazz> clazzes = clazzDao.queryAllClazz();
        if (CollectionUtils.isEmpty(clazzes)) {
            return Collections.EMPTY_LIST;
        }
        List<ClazzDto> clazzDtos = clazzes.stream().map(clazz -> {
            ClazzDto clazzDto = new ClazzDto();
            BeanUtils.copyProperties(clazz, clazzDto);
            return clazzDto;
        }).collect(Collectors.toList());
        return clazzDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteClazz(Long clazzId) {
        List<Clazz> clazzes = clazzDao.getByClazzId(clazzId);
        if (CollectionUtils.isEmpty(clazzes)) {
            return DeleteClazzStatus.NOT_SUCH_COURSE.getMessage();
        }
        List<CourseTeacherClazzDto> courseTeacherClazzDtos = courseTeacherClazzService.getByClazzId(clazzId);
        if (!CollectionUtils.isEmpty(courseTeacherClazzDtos)) {
            return DeleteClazzStatus.HAVE_RELEVANCE.getMessage();
        }
        Clazz clazz = clazzes.stream()
                .findFirst()
                .orElse(new Clazz());
        // 将Student学生对应班级id置为空
        studentService.updateByClazzId(clazzId);
        // 软删除Clazz表记录
        clazz.setStudentCount(0L);
        clazz.setIsdel(FieldIsdelStatus.ISDEL_TRUE.getIsdel());
        clazzDao.updateByClazzId(clazz);
        // 软删除ClazzStudent表记录
        List<ClazzStudentDto> clazzStudentDtos = clazzStudentService.getByClazzId(clazzId);
        clazzStudentDtos = clazzStudentDtos.stream()
                .map(clazzStudentDto -> clazzStudentDto.setIsdel(FieldIsdelStatus.ISDEL_TRUE.getIsdel()))
                .collect(Collectors.toList());
        clazzStudentDtos.forEach(clazzStudentDto -> clazzStudentService.updateByClazzStudentId(clazzStudentDto));
        return DeleteClazzStatus.DELETE_SUCCESS.getMessage();
    }

    @Override
    public ClazzDto getByClazzId(Long clazzId) {
        List<Clazz> clazzes = clazzDao.getByClazzId(clazzId);
        if (CollectionUtils.isEmpty(clazzes)) {
            return new ClazzDto();
        }
        Clazz clazz = clazzes.stream()
                .findFirst()
                .orElse(new Clazz());
        ClazzDto clazzDto = new ClazzDto();
        BeanUtils.copyProperties(clazz, clazzDto);
        return clazzDto;
    }

    @Override
    public List<ClazzDto> getBySearchClazzName(String searchClazzName) {
        List<Clazz> clazzes = clazzDao.getBySearchClazzName(searchClazzName);
        return clazzes.stream()
                .map(clazz -> {
                    ClazzDto clazzDto = new ClazzDto();
                    BeanUtils.copyProperties(clazz, clazzDto);
                    return clazzDto;
                })
                .collect(Collectors.toList());
    }
}
