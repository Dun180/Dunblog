package com.dun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dun.common.lang.Result;
import com.dun.entity.Comment;
import com.dun.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
