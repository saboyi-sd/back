package com.office.yancao.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class VerificationData {

    private Long id;

    private String batchId;
    private String brand;
    private String segment;

    // 使用 Map<String, Object> 接收 JSON，也可用自定义类
    private Map<String, Object> verificationResult;

    private Integer dataCount;
    private Integer operatorId;
    private LocalDateTime verifiedTime;
}
