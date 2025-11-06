package com.office.yancao.mapper;

import com.office.yancao.entity.VerificationData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VerificationDataMapper {
     void insert(VerificationData data);

     /**
      * 根据 batchId 和 segment 查询一条记录
      */
     VerificationData selectOneByBatchIdAndSegment(@Param("batchId") String batchId, @Param("segment") String segment);
}
