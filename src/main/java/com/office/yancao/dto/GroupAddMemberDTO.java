package com.office.yancao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupAddMemberDTO {
    private Long groupId;
    private List<Long> userIds;
    private Long operatorId;
}
