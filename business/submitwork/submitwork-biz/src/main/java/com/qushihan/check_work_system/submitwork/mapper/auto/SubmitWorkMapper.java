package com.qushihan.check_work_system.submitwork.mapper.auto;

import com.qushihan.check_work_system.submitwork.model.auto.SubmitWork;
import com.qushihan.check_work_system.submitwork.model.auto.SubmitWorkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubmitWorkMapper {
    long countByExample(SubmitWorkExample example);

    int deleteByExample(SubmitWorkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubmitWork record);

    int insertSelective(SubmitWork record);

    List<SubmitWork> selectByExample(SubmitWorkExample example);

    SubmitWork selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubmitWork record, @Param("example") SubmitWorkExample example);

    int updateByExample(@Param("record") SubmitWork record, @Param("example") SubmitWorkExample example);

    int updateByPrimaryKeySelective(SubmitWork record);

    int updateByPrimaryKey(SubmitWork record);
}