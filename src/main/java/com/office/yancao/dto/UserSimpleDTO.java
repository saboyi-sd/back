package com.office.yancao.dto;

import lombok.Data;

@Data
public class UserSimpleDTO {
    private Long id;
    private String username;   // 登录名
    private String position;   // 职位

    // 构造方法（可选）
    public UserSimpleDTO() {}

    public UserSimpleDTO(Long id, String username, String position) {
        this.id = id;
        this.username = username;
        this.position = position;
    }
}
