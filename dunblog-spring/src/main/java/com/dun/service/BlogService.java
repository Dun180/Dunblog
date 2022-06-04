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
    IPage<Map<String,Object>> getBlogList(IPage<Map<String,Object>> page, Integer state);


    /**
     * 根据分类id获取blog信息列表
     */
    IPage<Map<String,Object>> getBlogListByCategoryId(Integer id,IPage<Map<String,Object>> page);


    /**
     * 通过ID查询博客详细数据
     *
     */
    Map<String,Object> getBlogDetailById(Integer id);

    /**
     * 通过ID查询博客标签数据
     */
    List<Map<String,Object>> getBlogTagInfo(Integer id);

    /**
    * 获取日历热图数据
    */
    List<Map<String,Object>> getCalendarHeatmapData() throws Exception;
}
