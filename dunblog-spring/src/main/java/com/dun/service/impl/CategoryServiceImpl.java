package com.dun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Category;
import com.dun.mapper.CategoryMapper;
import com.dun.service.CategoryService;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
