package com.dun.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_tag_rel")
public class BlogTagRel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer blogId;

    private Integer tagId;

}
