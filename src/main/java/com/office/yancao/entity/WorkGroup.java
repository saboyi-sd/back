package com.office.yancao.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WorkGroup {
    private Long id;
    private String name;
    private String type; // 对应 enum('FIXED','DYNAMIC')
    private String description;
    private Long creatorId;
    private Boolean status; // tinyint(1) 映射为 Boolean
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}