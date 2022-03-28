package com.dun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Blog;
import com.dun.mapper.BlogMapper;
import com.dun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;


    @Override
    public List<Map<String, Object>> getBlogList(IPage<Map<String, Object>> page) {
        return blogMapper.getBlogList(page);
    }
}
