package com.qushihan.check_work_system.clazz.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public String deleteClazz(Long clazzId) {
        Clazz clazz = clazzDao.queryClazzListByClazzId(clazzId).stream().findFirst().orElse(null);
        // 将Student学生对应班级id置为空
        List<StudentDto> studentDtos = studentService.queryStudentDtoListByClazzId(clazzId).stream().map(
                studentDto -> studentDto.setClazzName(null).setClazzId(null)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(studentDtos)) {
            studentService.batchUpdateStudentByStudentId(studentDtos);
        }
        // 软删除Clazz表记录
        clazz.setStudentCount(0L);
        clazz.setIsdel(FieldIsdelStatus.ISDEL_TRUE.getIsdel());
        clazzDao.updateClazzByClazzId(clazz, clazzId);
        // 物理删除ClazzStudent表记录
        clazzStudentService.deleteClazzStudentByClazzId(clazzId);
        return DeleteClazzStatus.DELETE_SUCCESS.getMessage();
    }

    @Override
    public ClazzDto queryClazzDtoByClazzId(Long clazzId) {
        List<Clazz> clazzes = clazzDao.queryClazzListByClazzId(clazzId);
        Clazz clazz = clazzes.stream().findFirst().orElse(null);
        if (Optional.ofNullable(clazz).isPresent()) {
            ClazzDto clazzDto = new ClazzDto();
            BeanUtils.copyProperties(clazz, clazzDto);
            return clazzDto;
        }
        return new ClazzDto();
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
