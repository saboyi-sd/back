package com.office.yancao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 工段类型表实体类
 */
@Data
public class SectionType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;          // 主键ID
    private String sectionName;  // 工段名称（如"真空回潮段"）
    private String type;         // 工段类型（叶线/丝线）
}