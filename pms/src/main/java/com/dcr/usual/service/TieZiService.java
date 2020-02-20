package com.dcr.usual.service;

import com.dcr.usual.bean.TieZi;

import java.util.List;

public interface TieZiService {
    void saveInfo(TieZi tieZi);

    List<TieZi> getTieziLimitedList();
}
