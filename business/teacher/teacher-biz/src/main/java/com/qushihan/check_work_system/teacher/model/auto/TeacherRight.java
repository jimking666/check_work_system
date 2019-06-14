package com.qushihan.check_work_system.teacher.model.auto;

import java.util.Date;

public class TeacherRight {
    private Long id;

    private Long teacherRightId;

    private Long teacherId;

    private Integer rightLevel;

    private Boolean isdel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherRightId() {
        return teacherRightId;
    }

    public void setTeacherRightId(Long teacherRightId) {
        this.teacherRightId = teacherRightId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getRightLevel() {
        return rightLevel;
    }

    public void setRightLevel(Integer rightLevel) {
        this.rightLevel = rightLevel;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}