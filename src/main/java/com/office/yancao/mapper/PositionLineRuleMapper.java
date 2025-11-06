package com.office.yancao.mapper;

import com.office.yancao.entity.PositionLineRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位线组权限Mapper接口
 */
@Mapper
public interface PositionLineRuleMapper {
    /**
     * 根据岗位ID查询允许的线组列表
     * @param positionId 岗位ID
     * @return 允许的线组列表
     */
    List<PositionLineRule> selectByPositionId(@Param("positionId") Integer positionId);
}