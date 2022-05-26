package com.dun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Comment;
import com.dun.mapper.CommentMapper;
import com.dun.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentListByBlogId(Integer blogId) {
        return commentMapper.getCommentListByBlogId(blogId);
    }
}
