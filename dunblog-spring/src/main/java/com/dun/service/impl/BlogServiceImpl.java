package com.dun.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Blog;
import com.dun.mapper.BlogMapper;
import com.dun.service.BlogService;
import com.dun.util.DunUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Override
    public List<Map<String, Object>> getCalendarHeatmapData() throws Exception{
        List<Date> dateList = blogMapper.getCalendarHeatmapData();
        Map<String, Object> map = new HashMap<>();
        for (Date date: dateList) {
            String str = DateUtil.format(date, "yyyy-MM-dd");
            if (map.containsKey(str)){
                map.put(str,(Integer)(map.get(str))+1);
            }else {
                map.put(str,1);
            }
        }
        List<Map<String, Object>> result = DunUtils.mapToListMap(map, "date", "count");
        return result;
    }
}
