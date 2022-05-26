package com.dun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dun.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    /**
     * 根据博客id获取评论列表
     */
    List<Comment> getCommentListByBlogId(Integer blogId);
}
