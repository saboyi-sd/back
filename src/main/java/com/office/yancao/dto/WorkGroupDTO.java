package com.office.yancao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkGroupDTO {
    private Long id;
    private String name;
    private String description;
    private Long creatorId;

    private List<UserSimpleDTO> members; // ← 关键：嵌套成员列表
}
