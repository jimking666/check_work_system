package com.qushihan.check_work_system.clazz.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClazzDto {

    /**
     * 班级id
     */
    private Long clazzId;

    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 学生数量
     */
    private Long studentCount;

    /**
     * 是否删除
     */
    private Boolean isdel;

    /**
     * 创建日期
     */
    private Date createTime;
}
