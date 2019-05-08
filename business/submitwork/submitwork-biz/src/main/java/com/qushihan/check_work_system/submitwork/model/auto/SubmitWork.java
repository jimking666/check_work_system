package com.qushihan.check_work_system.submitwork.model.auto;

import java.util.Date;

public class SubmitWork {
    private Long id;

    private Long submitWorkId;

    private String submitWorkContent;

    private Float highRepetitiveRate;

    private Integer score;

    private Long workId;

    private Long studentId;

    private Boolean isdel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubmitWorkId() {
        return submitWorkId;
    }

    public void setSubmitWorkId(Long submitWorkId) {
        this.submitWorkId = submitWorkId;
    }

    public String getSubmitWorkContent() {
        return submitWorkContent;
    }

    public void setSubmitWorkContent(String submitWorkContent) {
        this.submitWorkContent = submitWorkContent == null ? null : submitWorkContent.trim();
    }

    public Float getHighRepetitiveRate() {
        return highRepetitiveRate;
    }

    public void setHighRepetitiveRate(Float highRepetitiveRate) {
        this.highRepetitiveRate = highRepetitiveRate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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