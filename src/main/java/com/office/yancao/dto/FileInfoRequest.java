package com.office.yancao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfoRequest {
    private String fileUrl;
    private String fileName;
    private String description;
}
