package com.qushihan.check_work_system.teacher.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeacherRightDto {

    /**
     * 教师权限id
     */
    private Long teacherRightId;

    /**
     * 教师id
     */
    private Long teacherId;

    /**
     * 权限等级 0：没有删除功能 1：具有删除功能
     */
    private Integer rightLevel;

    /**
     * 是否删除 ？0未删除 1删除
     */
    private Boolean isdel;
}
