package com.qushihan.check_work_system.core.model.auto;

import java.util.Date;

public class CourseTeacherClazz {
    private Long id;

    private Long courseTeacherClazzId;

    private Long courseId;

    private Long teacherId;

    private Long clazzId;

    private Long workCount;

    private Boolean isdel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseTeacherClazzId() {
        return courseTeacherClazzId;
    }

    public void setCourseTeacherClazzId(Long courseTeacherClazzId) {
        this.courseTeacherClazzId = courseTeacherClazzId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public Long getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Long workCount) {
        this.workCount = workCount;
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