package com.qushihan.check_work_system.work.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.qushihan.check_work_system.inf.util.GetIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.core.api.CourseTeacherClazzService;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.work.api.WorkService;
import com.qushihan.check_work_system.work.dao.WorkDao;
import com.qushihan.check_work_system.work.dto.WorkDto;
import com.qushihan.check_work_system.work.enums.CreateWorkStatus;
import com.qushihan.check_work_system.work.enums.DeleteWorkStatus;
import com.qushihan.check_work_system.work.model.auto.Work;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkDao workDao;

    @Autowired
    private CourseTeacherClazzService courseTeacherClazzService;

    @Override
    public List<WorkDto> queryWorkDtoListByCourseTeacherClazzId(Long courseTeacherClazzId) {
        List<Work> works = workDao.queryWorkListByCourseTeacherClazzId(courseTeacherClazzId);
        if (CollectionUtils.isEmpty(works)) {
            return Collections.EMPTY_LIST;
        }
        List<WorkDto> workDtos = works.stream().map(work -> {
            WorkDto workDto = new WorkDto();
            BeanUtils.copyProperties(work, workDto);
            return workDto;
        }).collect(Collectors.toList());
        return workDtos;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String createWork(String workTitle, String workContent, Float repetitiveRate, Long courseTeacherClazzId) {
        // 先查重复
        if (!CollectionUtils.isEmpty(workDao.queryWorkListByWorkTitleAndWorkContent(workTitle, workContent))) {
            return CreateWorkStatus.REPEAT_CREATE_FAIL.getMessage();
        }
        // 若不重复则插入
        Long workId = GetIdUtil.getId();
        Work work = new Work();
        work.setWorkId(workId);
        work.setWorkTitle(workTitle);
        work.setWorkContent(workContent);
        work.setRepetitiveRate(repetitiveRate);
        work.setCourseTeacherClazzId(courseTeacherClazzId);
        workDao.createWork(work);
        // 插入成功后将course_teacher_clazz.work_count字段加1
        courseTeacherClazzService.workCountAddOne(courseTeacherClazzId);
        return CreateWorkStatus.CREATE_SUCCESS.getMessage();
    }

    @Override
    public String deleteWork(Long workId) {
        Work work = new Work();
        work.setIsdel(FieldIsdelStatus.ISDEL_TRUE.getIsdel());
        workDao.updateWorkByWorkId(work, workId);
        return DeleteWorkStatus.DELETE_SUCCESS.getMessage();
    }

    @Override
    public List<WorkDto> getBySearchWorkTitle(String searchWorkTitle) {
        List<Work> works = workDao.getBySearchWorkTitle(searchWorkTitle);
        return works.stream()
                .map(work -> {
                    WorkDto workDto = new WorkDto();
                    BeanUtils.copyProperties(work, workDto);
                    return workDto;
                })
                .collect(Collectors.toList());
    }
}
