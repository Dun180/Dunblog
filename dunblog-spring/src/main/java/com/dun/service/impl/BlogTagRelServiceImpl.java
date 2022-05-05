package com.dun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.BlogTagRel;
import com.dun.mapper.BlogTagRelMapper;
import com.dun.service.BlogTagRelService;
import org.springframework.stereotype.Service;

@Service("blogTagRelService")
public class BlogTagRelServiceImpl extends ServiceImpl<BlogTagRelMapper, BlogTagRel> implements BlogTagRelService {
}
