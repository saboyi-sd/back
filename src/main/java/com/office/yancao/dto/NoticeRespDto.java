package com.office.yancao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NoticeRespDto {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private Date createdAt;
    private String imageUrls; // 所有初始照片
    private int isRead;
    private int isBanner;
}
