package com.dcr.system.dao;

import java.util.List;

import com.dcr.system.bean.Sources;
import com.dcr.system.bean.SourcesExample;
import org.apache.ibatis.annotations.Param;

public interface SourcesMapper {
    long countByExample(SourcesExample example);

    int deleteByExample(SourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sources record);

    int insertSelective(Sources record);

    List<Sources> selectByExample(SourcesExample example);

    Sources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByExample(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByPrimaryKeySelective(Sources record);

    int updateByPrimaryKey(Sources record);

    List<Sources> getSourcesByPidAndEid(@Param("eid") Integer eid, @Param("pid") int pid);
}