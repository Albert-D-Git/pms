package com.dcr.usual.service;

import com.dcr.usual.bean.Baoxiao;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface BaoxiaoService {
    PageInfo<Baoxiao> getBaoxiaoList(Integer eid, Integer pageNo, Map<String, Object> paramMap);

    boolean saveInfo(Baoxiao baoxiao, HttpSession session);

    boolean del(String id);

    boolean edit(Baoxiao baoxiao);
}
