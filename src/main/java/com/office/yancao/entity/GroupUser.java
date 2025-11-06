package com.office.yancao.entity;

import lombok.Data;

@Data
public class GroupUser {
    private Integer id;
    private Long userId;
    private Long GroupId;
    private Long operatorId;

}
