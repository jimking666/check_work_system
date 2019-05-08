package com.qushihan.check_work_system.clazz.api;

import java.util.List;

import com.qushihan.check_work_system.clazz.dto.ClazzDto;

public interface ClazzService {

    /**
     * 创建班级
     *
     * @param clazzName
     *
     * @return
     */
    String createClazz(String clazzName);

    /**
     * 查询所有班级
     *
     * @return
     */
    List<ClazzDto> queryAllClazz();

    /**
     * 删除班级
     *
     * @param clazzId
     *
     * @return
     */
    String deleteClazz(Long clazzId);

    /**
     * 通过班级id查询班级dto
     *
     * @param clazzId
     *
     * @return
     */
    ClazzDto queryClazzDtoByClazzId(Long clazzId);
}
