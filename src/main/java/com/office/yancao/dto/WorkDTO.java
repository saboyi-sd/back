package com.office.yancao.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class WorkDTO {
    private Long userId;
    private String classes;
    private String line;
    private String number;
    private String brand;
    private String batchNo;
    private String yield;
    private String isRemark;
    private String remark;

    // ✅ 接收多张图片
    private List<String> images;
}
