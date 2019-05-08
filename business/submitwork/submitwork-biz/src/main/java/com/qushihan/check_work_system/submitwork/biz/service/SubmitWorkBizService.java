package com.qushihan.check_work_system.submitwork.biz.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qushihan.check_work_system.submitwork.dao.SubmitWorkDao;
import com.qushihan.check_work_system.submitwork.dto.SubmitWorkDto;
import com.qushihan.check_work_system.submitwork.model.auto.SubmitWork;

@Service
public class SubmitWorkBizService {

    @Autowired
    private SubmitWorkDao submitWorkDao;

    /**
     * 通过submitWorkIds获取SubmitWorkDto列表
     *
     * @param submitWorkIds
     *
     * @return
     */
    public List<SubmitWorkDto> getBySubmitWorkIds(List<Long> submitWorkIds) {
        List<SubmitWork> submitWorks = submitWorkDao.getBySubmitWorkIds(submitWorkIds);
        return submitWorks.stream()
                .map(submitWork -> {
                    SubmitWorkDto submitWorkDto = new SubmitWorkDto();
                    BeanUtils.copyProperties(submitWork, submitWorkDto);
                    return submitWorkDto;
                })
                .collect(Collectors.toList());
    }

    /**
     * 通过submitWorkId修改SubmitWorkDto
     * @param submitWorkDto
     * @return
     */
    public int editBySubmitWorkId(SubmitWorkDto submitWorkDto) {
        if (!Optional.ofNullable(submitWorkDto).isPresent()) {
            return 0;
        }
        SubmitWork submitWork = new SubmitWork();
        BeanUtils.copyProperties(submitWorkDto, submitWork);
        return submitWorkDao.editBySubmitWorkId(submitWork);
    }
}
