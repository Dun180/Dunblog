package com.dun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Blog;
import com.dun.mapper.BlogMapper;
import com.dun.service.BlogService;
import org.springframework.stereotype.Service;

@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
}
