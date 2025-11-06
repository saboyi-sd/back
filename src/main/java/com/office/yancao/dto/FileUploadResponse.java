package com.office.yancao.dto;

import lombok.Data;

@Data
public class FileUploadResponse {
    private boolean success;
    private String message;
    private FileData data;

    // 构造方法
    public FileUploadResponse(boolean success, String message, FileData data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
