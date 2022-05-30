package com.dun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Blog;
import com.dun.mapper.BlogMapper;
import com.dun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Resource
    BlogMapper blogMapper;


    @Override
    public IPage<Map<String, Object>> getBlogList(IPage<Map<String, Object>> page, Integer state) {
        return blogMapper.getBlogList(page,state);
    }

    @Override
    public IPage<Map<String, Object>> getBlogListByCategoryId(Integer id, IPage<Map<String, Object>> page) {
        return blogMapper.getBlogListByCategoryId(id,page);
    }

    @Override
    public Map<String, Object> getBlogDetailById(Integer id) {
        return blogMapper.getBlogDetailById(id);
    }

    @Override
    public List<Map<String, Object>> getBlogTagInfo(Integer id) {
        return blogMapper.getBlogTagInfo(id);
    }
}
