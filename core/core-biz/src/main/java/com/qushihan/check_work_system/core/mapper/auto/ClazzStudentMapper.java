package com.qushihan.check_work_system.core.mapper.auto;

import com.qushihan.check_work_system.core.model.auto.ClazzStudent;
import com.qushihan.check_work_system.core.model.auto.ClazzStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClazzStudentMapper {
    long countByExample(ClazzStudentExample example);

    int deleteByExample(ClazzStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ClazzStudent record);

    int insertSelective(ClazzStudent record);

    List<ClazzStudent> selectByExample(ClazzStudentExample example);

    ClazzStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ClazzStudent record, @Param("example") ClazzStudentExample example);

    int updateByExample(@Param("record") ClazzStudent record, @Param("example") ClazzStudentExample example);

    int updateByPrimaryKeySelective(ClazzStudent record);

    int updateByPrimaryKey(ClazzStudent record);
}