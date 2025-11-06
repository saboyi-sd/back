package com.office.yancao.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限检查结果DTO
 */
@Data
public class PermissionCheckResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean allow;       // 是否允许操作
    private String pagePath;     // 跳转页面路径（allow为true时返回）
    private String reason;       // 不允许的原因（allow为false时返回）
}