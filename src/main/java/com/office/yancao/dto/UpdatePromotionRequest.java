package com.office.yancao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePromotionRequest {
    private Long articleId; // 或 Long，根据实际类型
    private Boolean isBanner;
}
