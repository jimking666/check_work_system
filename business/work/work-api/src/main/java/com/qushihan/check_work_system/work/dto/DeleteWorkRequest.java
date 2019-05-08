package com.qushihan.check_work_system.work.dto;

import lombok.Data;

/**
 * 删除作业请求
 */
@Data
public class DeleteWorkRequest {

    /**
     * 作业id
     */
    private String workId;
}
