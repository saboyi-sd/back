package com.office.yancao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 岗位基础信息表实体类
 */
@Data
public class PositionBase implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;          // 主键ID
    private String positionName; // 岗位名称（如"片烟入库""机械手A"）
    private String section;      // 所属工段（关联section_type的section_name）
    private String pagePath;     // 岗位对应的跳转页面路径
    private String lineType;     // 线组类型（A/B，含A则为A；含B或B/C则为B；无字母则为null）
}