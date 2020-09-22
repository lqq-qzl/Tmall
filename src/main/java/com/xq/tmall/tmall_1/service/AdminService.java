package com.xq.tmall.tmall_1.service;

import com.xq.tmall.tmall_1.entity.Admin;
import com.xq.tmall.tmall_1.util.PageUtil;

import java.util.List;

public interface AdminService {
    boolean add(Admin admin);
    boolean update(Admin admin);

    List<Admin> getList(String admin_name, PageUtil pageUtil);
    Admin get(String admin_name, Integer admin_id);
    Integer login(String admin_name, String admin_password);
    Integer getTotal(String admin_name);
}
