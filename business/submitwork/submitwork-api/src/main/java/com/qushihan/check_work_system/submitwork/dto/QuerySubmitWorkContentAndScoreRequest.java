package com.qushihan.check_work_system.submitwork.dto;

import lombok.Data;

/**
 * 查询提交作业分数请求
 */
@Data
public class QuerySubmitWorkContentAndScoreRequest {

    /**
     * 提交作业id
     */
    private String submitWorkId;
}
