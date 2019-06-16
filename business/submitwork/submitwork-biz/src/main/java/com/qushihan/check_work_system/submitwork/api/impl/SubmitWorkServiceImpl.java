package com.qushihan.check_work_system.submitwork.api.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.student.api.StudentService;
import com.qushihan.check_work_system.student.dto.StudentDto;
import com.qushihan.check_work_system.submitwork.api.SubmitWorkService;
import com.qushihan.check_work_system.submitwork.biz.service.SubmitWorkBizService;
import com.qushihan.check_work_system.submitwork.dao.SubmitWorkDao;
import com.qushihan.check_work_system.submitwork.dto.SubmitWorkDto;
import com.qushihan.check_work_system.submitwork.model.auto.SubmitWork;

@Service
public class SubmitWorkServiceImpl implements SubmitWorkService {

    @Autowired
    private SubmitWorkDao submitWorkDao;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubmitWorkBizService submitWorkBizService;

    @Override
    public List<SubmitWorkDto> getByWorkId(Long workId) {
        List<SubmitWork> submitWorks = submitWorkDao.getByWorkId(workId);
        if (CollectionUtils.isEmpty(submitWorks)) {
            return Collections.emptyList();
        }
        List<Long> studentIds = submitWorks.stream()
                .map(SubmitWork::getStudentId)
                .collect(Collectors.toList());
        List<StudentDto> studentDtos = studentService.getByStudentIdList(studentIds);
        Map<Long, StudentDto> studentDtoMap = studentDtos.stream()
                .collect(Collectors.toMap(StudentDto::getStudentId, Function.identity()));
        List<SubmitWorkDto> submitWorkDtos = submitWorks.stream().map(submitWork -> {
            SubmitWorkDto submitWorkDto = new SubmitWorkDto();
            BeanUtils.copyProperties(submitWork, submitWorkDto);
            Long studentId = Optional.ofNullable(submitWork.getStudentId())
                    .orElse(0L);
            String studentName = Optional.ofNullable(studentDtoMap.get(studentId))
                    .map(StudentDto::getStudentName)
                    .orElse("");
            submitWorkDto.setStudentName(studentName);
            return submitWorkDto;
        }).collect(Collectors.toList());
        return submitWorkDtos;
    }

    @Override
    public String queryContentAndScoreBySubmitWorkIds(Long submitWorkId) {
        List<SubmitWorkDto> submitWorkDtos = submitWorkBizService.getBySubmitWorkIds(Collections.singletonList(submitWorkId));
        if (CollectionUtils.isEmpty(submitWorkDtos)) {
            return "+_+";
        }
        SubmitWorkDto submitWorkDto = submitWorkDtos.stream()
                .findFirst()
                .orElse(new SubmitWorkDto());
        String submitWorkContent = Optional.ofNullable(submitWorkDto)
                .map(SubmitWorkDto::getSubmitWorkContent)
                .orElse("");
        String score = submitWorkDto.getScore() == null ? "" : String.valueOf(submitWorkDto.getScore());
        return submitWorkContent + "+_+" + score;
    }

    @Override
    public String saveScoreBySubmitWorkId(Long submitWorkId, Integer score) {
        List<SubmitWorkDto> submitWorkDtos = submitWorkBizService.getBySubmitWorkIds(Collections.singletonList(submitWorkId));
        SubmitWorkDto submitWorkDto = submitWorkDtos.stream()
                .findFirst()
                .get();
       if (!Optional.ofNullable(submitWorkDto).isPresent()) {
           return "保存失败";
       }
       submitWorkDto.setScore(score);
       submitWorkBizService.updateBySubmitWorkId(submitWorkDto);
       return "保存成功";
    }

    @Override
    public int updateBySubmitWorkId(SubmitWorkDto submitWorkDto) {
        return submitWorkBizService.updateBySubmitWorkId(submitWorkDto);
    }
}
