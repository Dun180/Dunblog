package com.dun.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog")
public class Blog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer creatorId;
    private String title;
    private String content;
    private Integer categoryId;
//    状态 0 已删除 1 已发布 2 未发布
    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
