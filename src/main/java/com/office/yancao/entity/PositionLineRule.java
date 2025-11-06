package com.office.yancao.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionLineRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer positionId;
    private String allowedLine;
}
