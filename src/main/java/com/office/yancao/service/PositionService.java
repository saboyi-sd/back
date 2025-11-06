package com.office.yancao.service;

import com.office.yancao.dto.PermissionCheckDTO;
import com.office.yancao.dto.PermissionCheckResultDTO;
import com.office.yancao.entity.PositionBase;
import com.office.yancao.entity.PositionLineRule;
import com.office.yancao.mapper.PositionBaseMapper;
import com.office.yancao.mapper.PositionLineRuleMapper;
import com.office.yancao.mapper.SectionTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位权限服务接口
 */
@Service
public class PositionService {

    @Autowired
    private PositionBaseMapper positionBaseMapper;

    @Autowired
    private PositionLineRuleMapper positionLineRuleMapper;

    @Autowired
    private SectionTypeMapper sectionTypeMapper;
    /**
     * 检查用户岗位对当前线组的操作权限
     * @param permissionCheckDTO 权限检查请求参数
     * @return 权限检查结果
     */
    public PermissionCheckResultDTO checkPermission(PermissionCheckDTO permissionCheckDTO) {
        PermissionCheckResultDTO result = new PermissionCheckResultDTO();

        // 1. 根据岗位名称查询岗位信息
        PositionBase positionBase = positionBaseMapper.selectByPositionName(permissionCheckDTO.getPosition());
        if (positionBase == null) {
            result.setAllow(false);
            result.setReason("未找到该岗位信息");
            return result;
        }

        // 2. 查询该岗位允许的线组列表
        List<PositionLineRule> allowedLines = positionLineRuleMapper.selectByPositionId(positionBase.getId());

        // 3. 检查是否允许操作当前线组
        boolean hasPermission = false;
        StringBuilder allowedLinesStr = new StringBuilder();

        for (PositionLineRule rule : allowedLines) {
            if ("*".equals(rule.getAllowedLine())) {
                // 允许所有线组
                hasPermission = true;
                break;
            }
            allowedLinesStr.append(rule.getAllowedLine()).append("/");
            if (rule.getAllowedLine().equals(permissionCheckDTO.getCurrentLine())) {
                hasPermission = true;
            }
        }

        if (hasPermission) {
            // 4. 允许操作，返回跳转页面
            result.setAllow(true);
            result.setPagePath(positionBase.getPagePath());
            result.setReason("");
        } else {
            // 5. 不允许操作，返回原因
            result.setAllow(false);
            // 构建不允许的原因，移除最后的斜杠
            String allowedLinesText = allowedLinesStr.length() > 0 ?
                    allowedLinesStr.substring(0, allowedLinesStr.length() - 1) : "";
            result.setReason(permissionCheckDTO.getPosition() + "仅允许" + allowedLinesText + "线组");
        }

        return result;
    }
}