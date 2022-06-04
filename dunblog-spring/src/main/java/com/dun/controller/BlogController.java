package com.dun.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dun.common.lang.Result;
import com.dun.entity.Blog;
import com.dun.service.BlogService;
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
                return Result.succ(blog.getId());
            }else{
                return Result.fail("提交失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }

    @GetMapping("/list")
    public Result getBlogList(@RequestParam(value = "currentPage") Long currentPage,@RequestParam(value = "pageSize") Long pageSize, @RequestParam(value = "state",required = false) Integer state){

        try{
            //调整currentPage，防止出错
            if(currentPage == null || currentPage < 1) currentPage = (long)1;
            Page<Map<String, Object>> page = new Page<>(currentPage,pageSize);
            IPage<Map<String, Object>> iPage = blogService.getBlogList(page, state);
            //获取查询结果，进行处理
            List<Map<String, Object>> blogList = iPage.getRecords();
            for(Map<String, Object> map:blogList){
                //计算content长度
                int count = map.get("content").toString().length();
                map.put("readTime",count/300+1);
                if (count > 100){
                    //大于100时截取前100个字符
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

    @GetMapping("/like")
    public Result likeBlog(@RequestParam(value = "blogId") Integer blogId){
        try {
            return Result.succ(blogService.update(new UpdateWrapper<Blog>().eq("id",blogId).setSql("`like` = `like` + 1")));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 获取日历热图数据
     */
    @GetMapping("/date")
    public Result getCalendarHeatmapData() {
        try {
            return Result.succ(blogService.getCalendarHeatmapData());
        }catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
