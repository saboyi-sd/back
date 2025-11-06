package com.office.yancao.service;

import com.office.yancao.entity.VerificationData;
import com.office.yancao.mapper.VerificationDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationDataService {

    @Autowired
    private VerificationDataMapper verificationDataMapper;

    public void save(VerificationData data) {
        verificationDataMapper.insert(data);
    }

    // 根据批次号和位置查询
    public VerificationData getOneByBatchIdAndSegment(String  batchId, String segment) {
        return verificationDataMapper.selectOneByBatchIdAndSegment(batchId, segment);
    }

}
