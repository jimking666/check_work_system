package com.qushihan.check_work_system.submitwork.api;

import java.util.List;

import com.qushihan.check_work_system.submitwork.dto.SubmitWorkDto;

public interface SubmitWorkService {

    /**
     * 通过作业id查询提交作业Dto列表
     *
     * @param workId
     *
     * @return
     */
    List<SubmitWorkDto> getByWorkId(Long workId);

    /**
     * 通过提交作业id获取提交作业内容和分数
     *
     * @param submitWorkId
     *
     * @return
     */
    String queryContentAndScoreBySubmitWorkIds(Long submitWorkId);

    /**
     * 保存分数
     * @param submitWorkId
     * @param score
     * @return
     */
    String saveScoreBySubmitWorkId(Long submitWorkId, Integer score);

    /**
     * 通过提交作业id修改提交作业记录
     *
     * @param submitWorkDto
     * @return
     */
    int updateBySubmitWorkId(SubmitWorkDto submitWorkDto);

    /**
     * 通过作业id列表查询提交作业Dto列表
     *
     * @param workIds
     *
     * @return
     */
    List<SubmitWorkDto> getByWorkIds(List<Long> workIds);
}
