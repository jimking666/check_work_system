package com.qushihan.check_work_system.work.model.auto;

import java.util.Date;

public class Work {
    private Long id;

    private Long workId;

    private String workTitle;

    private String workContent;

    private Float repetitiveRate;

    private Integer submitWorkCount;

    private Long courseTeacherClazzId;

    private Boolean isdel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle == null ? null : workTitle.trim();
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent == null ? null : workContent.trim();
    }

    public Float getRepetitiveRate() {
        return repetitiveRate;
    }

    public void setRepetitiveRate(Float repetitiveRate) {
        this.repetitiveRate = repetitiveRate;
    }

    public Integer getSubmitWorkCount() {
        return submitWorkCount;
    }

    public void setSubmitWorkCount(Integer submitWorkCount) {
        this.submitWorkCount = submitWorkCount;
    }

    public Long getCourseTeacherClazzId() {
        return courseTeacherClazzId;
    }

    public void setCourseTeacherClazzId(Long courseTeacherClazzId) {
        this.courseTeacherClazzId = courseTeacherClazzId;
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