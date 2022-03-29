package com.dun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dun.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * 获取blog信息列表
     */
    IPage<Map<String,Object>> getBlogList(IPage<Map<String,Object>> page);

    /**
     * 通过ID查询博客详细数据
     *
     */
    Map<String,Object> getBlogDetailById(Integer id);
}
