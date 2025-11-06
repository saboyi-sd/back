package com.office.yancao.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限检查请求DTO
 */
@Data
public class PermissionCheckDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String position;     // 用户岗位（从之前的接口获取）
    private String currentLine;  // 当前订单的线组（order.line）
}