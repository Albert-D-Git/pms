package com.dcr.system.dao;

import org.apache.ibatis.annotations.Param;

public interface RoleSourceMapper {
    void save(@Param("rid")Integer rid, @Param("sourcesId")Integer[] sourcesId);
}
