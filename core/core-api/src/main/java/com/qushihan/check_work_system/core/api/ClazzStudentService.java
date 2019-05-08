package com.qushihan.check_work_system.core.api;

public interface ClazzStudentService {

    /**
     * 通过班级id批量删除ClazzStudent记录
     *
     * @param clazzId
     *
     * @return
     */
    Integer deleteClazzStudentByClazzId(Long clazzId);
}
