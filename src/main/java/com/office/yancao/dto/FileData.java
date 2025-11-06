package com.office.yancao.dto;

import lombok.Data;


@Data
public class FileData {
    private String url;
    private String filename;
    private String originalName;
    private Long size;

    public FileData(String url, String filename, String originalName, Long size) {
        this.url = url;
        this.filename = filename;
        this.originalName = originalName;
        this.size = size;
    }
}
