package com.office.yancao.mapper;

import com.office.yancao.entity.SectionType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工段类型Mapper接口
 */
@Mapper
public interface SectionTypeMapper {
    /**
     * 根据工段名称查询工段类型
     * @param sectionName 工段名称
     * @return 工段类型实体
     */
    SectionType selectBySectionName(String sectionName);
}