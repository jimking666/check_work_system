package com.qushihan.check_work_system.submitwork.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import com.qushihan.check_work_system.submitwork.mapper.auto.SubmitWorkMapper;
import com.qushihan.check_work_system.submitwork.model.auto.SubmitWork;
import com.qushihan.check_work_system.submitwork.model.auto.SubmitWorkExample;

@Repository
public class SubmitWorkDao {

    @Autowired
    private SubmitWorkMapper submitWorkMapper;

    /**
     * 通过作业id查询提交作业列表
     *
     * @param workId
     *
     * @return
     */
    public List<SubmitWork> getByWorkId(Long workId) {
        if (!Optional.ofNullable(workId).isPresent()) {
            return Collections.emptyList();
        }
        SubmitWorkExample submitWorkExample = new SubmitWorkExample();
        SubmitWorkExample.Criteria criteria = submitWorkExample.createCriteria();
        criteria.andWorkIdEqualTo(workId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return submitWorkMapper.selectByExample(submitWorkExample);
    }

    /**
     * 通过提交submitWorkId列表查询SubmitWork列表
     *
     * @param submitWorkIds
     *
     * @return
     */
    public List<SubmitWork> getBySubmitWorkIds(List<Long> submitWorkIds) {
        if (CollectionUtils.isEmpty(submitWorkIds)) {
            return Collections.EMPTY_LIST;
        }
        SubmitWorkExample submitWorkExample = new SubmitWorkExample();
        SubmitWorkExample.Criteria criteria = submitWorkExample.createCriteria();
        criteria.andSubmitWorkIdIn(submitWorkIds);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return submitWorkMapper.selectByExample(submitWorkExample);
    }

    /**
     * 通过submitWorkId修改SubmitWork记录
     *
     * @param submitWork
     * @return
     */
    public int updateBySubmitWorkId(SubmitWork submitWork) {
        if (!Optional.ofNullable(submitWork).map(SubmitWork::getSubmitWorkId).isPresent()) {
            return 0;
        }
        SubmitWorkExample submitWorkExample = new SubmitWorkExample();
        SubmitWorkExample.Criteria criteria = submitWorkExample.createCriteria();
        criteria.andSubmitWorkIdEqualTo(submitWork.getSubmitWorkId());
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return submitWorkMapper.updateByExampleSelective(submitWork, submitWorkExample);
    }
}
