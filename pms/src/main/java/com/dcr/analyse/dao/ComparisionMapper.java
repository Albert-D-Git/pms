package com.dcr.analyse.dao;


import java.util.List;

import com.dcr.analyse.bean.Comparision;
import com.dcr.analyse.bean.ComparisionExample;
import org.apache.ibatis.annotations.Param;

public interface ComparisionMapper {
    long countByExample(ComparisionExample example);

    int deleteByExample(ComparisionExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Comparision record);

    int insertSelective(Comparision record);

    List<Comparision> selectByExample(ComparisionExample example);

    Comparision selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Comparision record, @Param("example") ComparisionExample example);

    int updateByExample(@Param("record") Comparision record, @Param("example") ComparisionExample example);

    int updateByPrimaryKeySelective(Comparision record);

    int updateByPrimaryKey(Comparision record);
}