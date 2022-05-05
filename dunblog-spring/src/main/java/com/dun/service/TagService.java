package com.dun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dun.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    /**
     * 根据博客id获取标签id
     */
    List<Integer> getTagIdByBlogId(Integer id);
}
