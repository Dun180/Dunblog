package com.dun.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("to_do_list")
public class ToDoList implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String matter;
    private String tag;
    private Integer state;
    private Integer type;
    private Date startTime;
    private Date endTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
