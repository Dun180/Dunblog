package com.dun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Tag;

import com.dun.mapper.TagMapper;
import com.dun.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    TagMapper tagMapper;

    @Override
    public List<Integer> getTagIdByBlogId(Integer id) {
        return tagMapper.getTagIdByBlogId(id);
    }
}
