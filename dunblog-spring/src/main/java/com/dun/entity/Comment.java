package com.dun.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class Comment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer blogId;

//    一级评论的父评论为自己本身，二级评论的父评论为一级评论，三级评论的父评论为二级或三级评论
    private Integer parentId;

//    根评论id
    private Integer rootId;

//    评论类型：1 一级评论 2 二级评论 3 三级评论
    private Integer type;

//    评论状态：0 待审核 1 审核成功 2 审核失败
    private Integer state;

//    评论人姓名
    private String commentatorName;

//    评论内容
    private String content;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    @TableField(exist = false)
    private List<Comment> child;
}
