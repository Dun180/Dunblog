package com.dun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dun.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据博客id获取标签id
     */
    List<Integer> getTagIdByBlogId(Integer id);
}
