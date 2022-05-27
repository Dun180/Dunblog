package com.dun.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Comment;
import com.dun.mapper.CommentMapper;
import com.dun.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentListByBlogId(Integer blogId) {

        List<Comment> allComments = commentMapper.selectList(new QueryWrapper<Comment>().eq("blog_id",blogId));
        Map<Integer, Comment> map = new HashMap<>();
        List<Comment> result = new ArrayList<>();
        for (Comment comment : allComments) {
            comment.setInputActive(false);
            if (comment.getParentId() == null) {
                result.add(comment);
                map.put(comment.getId(), comment);
            }
        }
        for (Comment comment : allComments) {
            if (comment.getRootId() != null) {
                Comment root = map.get(comment.getRootId());
                if (root.getChild() == null) {
                    root.setChild(new ArrayList<>());
                }
                root.getChild().add(comment);
            }
        }
        return result;
    }
}
