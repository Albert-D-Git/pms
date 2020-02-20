package com.dcr.usual.dao;


import java.util.List;

import com.dcr.usual.bean.TieZi;
import com.dcr.usual.bean.TieZiExample;
import org.apache.ibatis.annotations.Param;

public interface TieZiMapper {
    long countByExample(TieZiExample example);

    int deleteByExample(TieZiExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(TieZi record);

    int insertSelective(TieZi record);

    List<TieZi> selectByExampleWithBLOBs(TieZiExample example);

    List<TieZi> selectByExample(TieZiExample example);

    TieZi selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") TieZi record, @Param("example") TieZiExample example);

    int updateByExampleWithBLOBs(@Param("record") TieZi record, @Param("example") TieZiExample example);

    int updateByExample(@Param("record") TieZi record, @Param("example") TieZiExample example);

    int updateByPrimaryKeySelective(TieZi record);

    int updateByPrimaryKeyWithBLOBs(TieZi record);

    int updateByPrimaryKey(TieZi record);
}