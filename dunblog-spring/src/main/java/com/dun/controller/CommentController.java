package com.dun.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dun.common.lang.Result;
import com.dun.entity.Comment;
import com.dun.service.CommentService;
import com.dun.util.DunUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;


    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment){
        try {
            comment.setState(0);

            return Result.succ(commentService.save(comment));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result deleteComment(@RequestBody Comment comment){
        try {
            boolean result = commentService.removeById(comment.getId());
            if (result){
                return Result.succ("操作成功");
            }else {
                return Result.fail("删除失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result getCommentListByBlogId(@RequestParam(value = "blogId") Integer blogId){
        try{
            return Result.succ(commentService.getCommentListByBlogId(blogId));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }

    @PostMapping("/query")
    public Result queryComment(@RequestBody Comment comment){
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("root_id",comment.getRootId());
            map.put("create_time",comment.getCreateTime());
            map.put("commentator_name",comment.getCommentatorName());
            map.put("state",comment.getState());
            map.put("blog_id",comment.getBlogId());
            map.put("parent_id",comment.getParentId());
            map.put("content",comment.getContent());
            List<Comment> list = commentService.list(new QueryWrapper<Comment>().allEq(map,false));

            return Result.succ(list);

        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
