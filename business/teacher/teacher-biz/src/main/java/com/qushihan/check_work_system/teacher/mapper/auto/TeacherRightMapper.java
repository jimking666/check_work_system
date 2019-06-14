package com.qushihan.check_work_system.teacher.mapper.auto;

import com.qushihan.check_work_system.teacher.model.auto.TeacherRight;
import com.qushihan.check_work_system.teacher.model.auto.TeacherRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherRightMapper {
    long countByExample(TeacherRightExample example);

    int deleteByExample(TeacherRightExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TeacherRight record);

    int insertSelective(TeacherRight record);

    List<TeacherRight> selectByExample(TeacherRightExample example);

    TeacherRight selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TeacherRight record, @Param("example") TeacherRightExample example);

    int updateByExample(@Param("record") TeacherRight record, @Param("example") TeacherRightExample example);

    int updateByPrimaryKeySelective(TeacherRight record);

    int updateByPrimaryKey(TeacherRight record);
}