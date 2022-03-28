package com.dun.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dun.common.lang.Result;
import com.dun.entity.Blog;
import com.dun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @PostMapping("/edit")
    public Result blogEdit(@RequestBody Blog blog){
        try{

            blog.setCreaterId(2);
            blog.setCategoryId(1);

            if(blogService.saveOrUpdate(blog)){
                return Result.succ(true);
            }else{
                return Result.fail("提交失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }

    @GetMapping("/list")
    public Result blogList(@RequestParam(value = "currentPage") Long currentPage){

        try{
            if(currentPage == null || currentPage < 1) currentPage = (long)1;
            Page<Map<String, Object>> page = new Page<>(currentPage,3);
            List<Map<String, Object>> blogList = blogService.getBlogList(page);
            for(Map<String, Object> map:blogList){
                int count = map.get("content").toString().length();
                map.put("count",count);

                if (count > 100){
                    map.put("content",map.get("content").toString().substring(0,100));
                }

            }
            return Result.succ(blogList);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }

    @GetMapping("/get")
    public Result getBlogById(@RequestParam(value = "blogId") Long blogId){
        try{
            Blog blog = blogService.getById(blogId);
            return Result.succ(blog);

        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
