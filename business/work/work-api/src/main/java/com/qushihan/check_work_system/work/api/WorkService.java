package com.qushihan.check_work_system.work.api;

import java.util.List;

import com.qushihan.check_work_system.work.dto.WorkDto;

public interface WorkService {

    /**
     * 通过CourseTeacherClazzId查询作业列表Dto
     *
     * @param courseTeacherClazzId
     *
     * @return
     */
    List<WorkDto> queryWorkDtoListByCourseTeacherClazzId(Long courseTeacherClazzId);


    /**
     * 创建作业
     *
     * @param workTitle
     * @param workContent
     * @param repetitiveRate
     * @param courseTeacherClazzId
     *
     * @return
     */
    String createWork(String workTitle, String workContent, Float repetitiveRate, Long courseTeacherClazzId);

    /**
     * 删除作业
     *
     * @param workId
     *
     * @return
     */
    String deleteWork(Long workId);
}
