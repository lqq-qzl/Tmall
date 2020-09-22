package com.xq.tmall.tmall_1.service.impl;

import com.xq.tmall.tmall_1.dao.LastIDMapper;
import com.xq.tmall.tmall_1.service.LastIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LastIDServiceImpl implements LastIDService {
    @Autowired
    private LastIDMapper lastIDMapper;

    public void setLastIDMapper(LastIDMapper lastIDMapper) {
        this.lastIDMapper = lastIDMapper;
    }

    @Override
    public int selectLastID() {
        return lastIDMapper.selectLastID();
    }
}
