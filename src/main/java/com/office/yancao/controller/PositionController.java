package com.office.yancao.controller;

import com.office.yancao.dto.PermissionCheckDTO;
import com.office.yancao.dto.PermissionCheckResultDTO;


import com.office.yancao.service.PositionService;
import com.office.yancao.untils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 岗位权限控制器
 */
@RestController
@RequestMapping("/api/position")

public class PositionController {

    @Resource
    private PositionService positionService;

    /**
     * 根据岗位和当前线组，检查是否允许操作
     * @param permissionCheckDTO 请求参数
     * @return 权限检查结果
     */
    @PostMapping("/check-permission")
    public Result<PermissionCheckResultDTO> checkPermission(@RequestBody PermissionCheckDTO permissionCheckDTO) {
        // 参数验证
        if (permissionCheckDTO == null || permissionCheckDTO.getPosition() == null || permissionCheckDTO.getCurrentLine() == null) {
            return Result.fail("参数不完整");
        }

        // 调用服务层检查权限
        PermissionCheckResultDTO resultDTO = positionService.checkPermission(permissionCheckDTO);

        // 返回结果
        return Result.success(resultDTO);
    }
}