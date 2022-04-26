package com.dun.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dun.common.lang.Result;
import com.dun.entity.Blog;
import com.dun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    BlogService blogService;

    @PostMapping("/edit")
    public Result blogEdit(@RequestBody Blog blog){
        try{

            blog.setCreatorId(2);
//            blog.setCategoryId(1);

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
    public Result getBlogList(@RequestParam(value = "currentPage") Long currentPage,@RequestParam(value = "pageSize") Long pageSize){

        try{
            if(currentPage == null || currentPage < 1) currentPage = (long)1;
            Page<Map<String, Object>> page = new Page<>(currentPage,pageSize);
            IPage<Map<String, Object>> iPage = blogService.getBlogList(page);
            List<Map<String, Object>> blogList = iPage.getRecords();
            for(Map<String, Object> map:blogList){
                int count = map.get("content").toString().length();
                map.put("readTime",count/300+1);
                if (count > 100){
                    map.put("content",map.get("content").toString().substring(0,100));
                    String formatCount = String.format("%.1f", (float)count / 1000.0);
                    map.put("count",formatCount+'k');
                }else {
                    map.put("count",count);
                }
            }
            iPage.setRecords(blogList);
            return Result.succ(iPage);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }

    @GetMapping("/get")
    public Result getBlogDetailById(@RequestParam(value = "blogId") Integer blogId){
        try{
            Map<String, Object> map = blogService.getBlogDetailById(blogId);

            int count = map.get("content").toString().length();
            map.put("readTime",count/300+1);

            if (count > 100){
                String formatCount = String.format("%.1f", (float)count / 1000.0);
                map.put("count",formatCount+'k');
            }else {
                map.put("count",count);
            }
            return Result.succ(map);

        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result deleteBlog(@RequestBody Blog blog){
        try {
            System.out.println(blog);
            boolean result = blogService.removeById(blog.getId());
            if (result){
                return Result.succ("操作成功");
            }else {
                return Result.fail("删除失败");
            }

        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
