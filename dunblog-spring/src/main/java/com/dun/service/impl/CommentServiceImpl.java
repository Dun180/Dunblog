package com.dun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Comment;
import com.dun.mapper.CommentMapper;
import com.dun.service.CommentService;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
