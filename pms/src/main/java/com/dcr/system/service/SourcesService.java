package com.dcr.system.service;

import com.dcr.system.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourceListByPid(int i);

    void saveInfo(Sources sources);

    Sources getSourceById(Integer sid);

    void edit(Sources sources);

    void delteSourceById(Integer sid);

    List<Sources> getSecondSourcesListByEid(Integer eid);
}
