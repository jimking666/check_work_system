package com.qushihan.check_work_system.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.core.mapper.auto.ClazzStudentMapper;
import com.qushihan.check_work_system.core.model.auto.ClazzStudentExample;

@Repository
public class ClazzStudentDao {

    @Autowired
    private ClazzStudentMapper clazzStudentMapper;

    /**
     * 通过班级id批量删除ClazzStudent记录
     *
     * @param clazzId
     *
     * @return
     */
    public Integer deleteClazzStudentByClazzId(Long clazzId) {
        ClazzStudentExample clazzStudentExample = new ClazzStudentExample();
        ClazzStudentExample.Criteria criteria = clazzStudentExample.createCriteria();
        criteria.andClazzIdEqualTo(clazzId);
        return clazzStudentMapper.deleteByExample(clazzStudentExample);
    }
}
