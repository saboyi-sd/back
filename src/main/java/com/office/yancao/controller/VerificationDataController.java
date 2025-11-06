package com.office.yancao.controller;

import com.office.yancao.entity.VerificationData;
import com.office.yancao.service.VerificationDataService;
import com.office.yancao.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verification")
public class VerificationDataController {

    @Autowired
    private VerificationDataService verificationDataService;

    @PostMapping("/save")
    public Result<String> create(@RequestBody VerificationData data) {
        verificationDataService.save(data);
        return Result.success("保存成功");
    }


    @GetMapping("/byBatchIdAndSegment")
    public Result<VerificationData> getByBatchIdAndSegment(
            @RequestParam String batchId,
            @RequestParam String segment) {
        VerificationData data = verificationDataService.getOneByBatchIdAndSegment(batchId, segment);
        if (data == null) {
            return Result.fail("没有数据");
        }
        return Result.success(data);
    }

}
