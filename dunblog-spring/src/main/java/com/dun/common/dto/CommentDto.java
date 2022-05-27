package com.dun.common.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class CommentDto implements Serializable {
    private Integer id;
    private Integer parentId;
    private Integer blogId;
    private Integer type;
    private Integer state;
    private String commentatorName;
    private String content;

}
