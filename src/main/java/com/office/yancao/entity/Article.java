package com.office.yancao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    private Long id;
    private String title;
    private String content;
    private String text;
    private String coverImage;
    private String type;
    private Integer imageCount;
    private Integer createdBy;

    private Integer isBanner;

    private Integer isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    // 图片列表（非数据库字段，用于接收前端传递的图片数组）
    private List<String> images;

    // 用于接收前面传过来的数组
    private List<String> coverImages;
}
