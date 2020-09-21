package com.xq.tmall.tmall_1.service;

import com.xq.tmall.tmall_1.entity.User;
import com.xq.tmall.tmall_1.util.OrderUtil;
import com.xq.tmall.tmall_1.util.PageUtil;

import java.util.List;

public interface UserService {
    boolean add(User user);
    boolean update(User user);

    List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil);
    User get(Integer user_id);
    User login(String user_name, String user_password);
    Integer getTotal(User user);
}
