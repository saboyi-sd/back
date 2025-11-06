package com.office.yancao.mapper;

import com.office.yancao.entity.PositionBase;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位基础信息Mapper接口
 */
@Mapper
public interface PositionBaseMapper {
    /**
     * 根据岗位名称查询岗位信息
     * @param positionName 岗位名称
     * @return 岗位基础信息实体
     */
    PositionBase selectByPositionName(String positionName);
}