package com.xq.tmall.tmall_1.service;

import com.xq.tmall.tmall_1.entity.Category;
import com.xq.tmall.tmall_1.util.PageUtil;

import java.util.List;

public interface CategoryService {
    boolean add(Category category);
    boolean update(Category category);

    List<Category> getList(String category_name, PageUtil pageUtil);
    Category get(Integer category_id);
    Integer getTotal(String category_name);
}
