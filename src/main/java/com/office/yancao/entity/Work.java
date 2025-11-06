package com.office.yancao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Work {
    private Long id;
    private Long userId;
    private String classes; // 避免使用关键字class作为字段名
    private String line;
    private String number;
    private String brand;
    private String batchNo;
    private String yield;
    private String isRemark;
    private String remark;
    private String images;
    private LocalDateTime createTime;
}