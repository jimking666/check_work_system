package com.qushihan.check_work_system.core.dao;

import com.qushihan.check_work_system.core.model.auto.ClazzStudent;
import com.qushihan.check_work_system.inf.enums.FieldIsdelStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qushihan.check_work_system.core.mapper.auto.ClazzStudentMapper;
import com.qushihan.check_work_system.core.model.auto.ClazzStudentExample;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ClazzStudentDao {

    @Autowired
    private ClazzStudentMapper clazzStudentMapper;

    /**
     * 通过班级学生id更改clazzStudent记录
     *
     * @param clazzStudent
     *
     * @return
     */
    public int updateByClazzStudentId(ClazzStudent clazzStudent) {
        if (!Optional.ofNullable(clazzStudent).isPresent()) {
            return 0;
        }
        ClazzStudentExample clazzStudentExample = new ClazzStudentExample();
        ClazzStudentExample.Criteria criteria = clazzStudentExample.createCriteria();
        criteria.andClazzStudentIdEqualTo(clazzStudent.getClazzStudentId());
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzStudentMapper.updateByExampleSelective(clazzStudent, clazzStudentExample);
    }

    /**
     * 通过班级id查询clazzStudent记录
     *
     * @param clazzId
     * @return
     */
    public List<ClazzStudent> getByClazzId(Long clazzId) {
        if (!Optional.ofNullable(clazzId).isPresent()) {
            return Collections.emptyList();
        }
        ClazzStudentExample clazzStudentExample = new ClazzStudentExample();
        ClazzStudentExample.Criteria criteria = clazzStudentExample.createCriteria();
        criteria.andClazzIdEqualTo(clazzId);
        criteria.andIsdelEqualTo(FieldIsdelStatus.ISDEL_FALSE.getIsdel());
        return clazzStudentMapper.selectByExample(clazzStudentExample);
    }
}
