package com.qushihan.check_work_system.submitwork.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubmitWorkDto {

    /**
     * 提交作业id
     */
    private Long submitWorkId;

    /**
     * 提交作业内容
     */
    private String submitWorkContent;

    /**
     * 最高重复率
     */
    private Float highRepetitiveRate;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 作业id
     */
    private Long workId;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 是否删除
     */
    private Boolean isdel;

    /**
     * 创建日期
     */
    private Date createTime;
}
