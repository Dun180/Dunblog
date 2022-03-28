package com.dun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dun.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService extends IService<Blog> {

    /**
     * 获取blog信息列表
     */
    List<Map<String,Object>> getBlogList(IPage<Map<String,Object>> page);
}
