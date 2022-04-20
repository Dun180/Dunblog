package com.dun.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category")
public class Category {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer creatorId;
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
