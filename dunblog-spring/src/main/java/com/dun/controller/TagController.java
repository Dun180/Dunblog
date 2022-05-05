package com.dun.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dun.common.lang.Result;
import com.dun.entity.BlogTagRel;
import com.dun.entity.Category;
import com.dun.entity.Tag;
import com.dun.service.BlogTagRelService;
import com.dun.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    TagService tagService;

    @Resource
    BlogTagRelService blogTagRelService;

    @PostMapping("/add")
    public Result addTag(@RequestBody Tag tag){
        try {
            tag.setCreatorId(2);
            return Result.succ(tagService.save(tag));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result deleteTag(@RequestBody Tag tag){
        try {
            boolean result = tagService.removeById(tag.getId());
            if (result){
                return Result.succ("操作成功");
            }else {
                return Result.fail("删除失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result editTag(@RequestBody Tag tag){
        try {
            if(tag.getId()==1){
                return Result.fail("编辑失败");
            }
            boolean result = tagService.saveOrUpdate(tag);
            if (result){
                return Result.succ("操作成功");
            }else {
                return Result.fail("编辑失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result getTagList(){
        try{
            return Result.succ(tagService.list());
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/rel")
    public Result relBlogAndTag(@RequestBody Map<String,Object> map){
        try {

            int blogId = Integer.parseInt(map.get("blogId").toString());
            Object[] tags = JSON.parseArray(map.get("tags").toString()).toArray();

            //通过博客id查询到已应用到该博客的所有标签id
            List<Integer> tagIdList = tagService.getTagIdByBlogId(blogId);

            for (Object tag : tags) {

                Integer tagId = Integer.parseInt(tag.toString());

                //对已应用到该博客的所有标签id进行查重，重复的标签不再添加关联
                if (!tagIdList.contains(tagId)){
                    //如果没有重复，直接添加关联
                    BlogTagRel blogTagRel = new BlogTagRel();

                    blogTagRel.setBlogId(blogId);
                    blogTagRel.setTagId(tagId);

                    if (!blogTagRelService.saveOrUpdate(blogTagRel)){
                        return Result.fail("关联失败");
                    }
                }else {
                    //如果重复了，直接将重复的标签id移出数组，方便之后删除关联
                    tagIdList.remove(tagId);
                }
            }
            
            //最后将tagIdList中剩余未匹配的标签关联删除
            for (Integer item: tagIdList) {
                blogTagRelService.remove(new QueryWrapper<BlogTagRel>()
                        .eq("blog_id",blogId)
                        .eq("tag_id",item)
                );
            }
            
            return Result.succ(true);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }
}
