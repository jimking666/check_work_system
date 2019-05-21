package com.qushihan.check_work_system.submitwork.dto;

import lombok.Data;

/**
 * 搜索提交作业请求
 */
@Data
public class GetSubmitWorkBySearchRequest {

    /**
     * 搜索学生名字
     */
    private String searchStudentName;
}
