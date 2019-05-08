package com.qushihan.check_work_system.core.mapper.auto;

import com.qushihan.check_work_system.core.model.auto.CourseTeacherClazz;
import com.qushihan.check_work_system.core.model.auto.CourseTeacherClazzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseTeacherClazzMapper {
    long countByExample(CourseTeacherClazzExample example);

    int deleteByExample(CourseTeacherClazzExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseTeacherClazz record);

    int insertSelective(CourseTeacherClazz record);

    List<CourseTeacherClazz> selectByExample(CourseTeacherClazzExample example);

    CourseTeacherClazz selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseTeacherClazz record, @Param("example") CourseTeacherClazzExample example);

    int updateByExample(@Param("record") CourseTeacherClazz record, @Param("example") CourseTeacherClazzExample example);

    int updateByPrimaryKeySelective(CourseTeacherClazz record);

    int updateByPrimaryKey(CourseTeacherClazz record);
}