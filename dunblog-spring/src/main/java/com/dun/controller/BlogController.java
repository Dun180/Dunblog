package com.dun.controller;


import com.dun.common.lang.Result;
import com.dun.entity.Blog;
import com.dun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @PostMapping("/blogedit")
    public Result blogEdit(@RequestBody Map<String,Object> map){
        String title = map.get("title").toString();
        String content = map.get("content").toString();

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCreaterId(2);

        if(map.get("blogId")!=null&&map.get("blogId").toString()!=""){
            //如果博客存在 直接设置博客id，不需要自动递增
            blog.setId(Integer.parseInt(map.get("blogId").toString()));
        }
        if(blogService.saveOrUpdate(blog)){
            return Result.succ(true);
        }else{
            return Result.fail("提交失败");
        }
    }

    @GetMapping("/blog/{}")
    public Result blog(){




        return Result.succ(true);
    }
}
