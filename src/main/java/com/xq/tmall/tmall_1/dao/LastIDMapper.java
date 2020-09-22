package com.xq.tmall.tmall_1.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface LastIDMapper {
    int selectLastID();
}
