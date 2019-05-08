package com.qushihan.check_work_system.submitwork.dto;

import lombok.Data;

/**
 * 查询提交作业分数请求
 */
@Data
public class SaveScoreRequest {

    /**
     * 提交作业id
     */
    private String submitWorkId;

    /**
     * 分数
     */
    private String score;
}
