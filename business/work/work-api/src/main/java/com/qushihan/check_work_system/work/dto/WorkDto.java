package com.qushihan.check_work_system.work.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WorkDto {

    /**
     * 作业id
     */
    private Long workId;

    /**
     * 作业题目
     */
    private String workTitle;

    /**
     * 作业内容
     */
    private String workContent;

    /**
     * 重复率
     */
    private Float repetitiveRate;

    /**
     * 提交作业数量
     */
    private Integer submitWorkCount;

    /**
     * 课程教师班级id
     */
    private Long courseTeacherClazzId;

    /**
     * 是否删除
     */
    private Boolean isdel;

    /**
     * 创建日期
     */
    private Date createTime;
}
