package com.office.yancao.controller;

import com.office.yancao.dto.GroupAddMemberDTO;
import com.office.yancao.dto.WorkDTO;
import com.office.yancao.dto.WorkGroupDTO;
import com.office.yancao.entity.WorkGroup;
import com.office.yancao.service.WorkGroupService;
import com.office.yancao.service.WorkService;
import com.office.yancao.untils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/work-group")
public class WorkGroupController {

    @Resource
    private WorkGroupService workGroupService;

    /**
     * 保存动态分组
     */
    @PostMapping("/create")
    public Result<WorkGroup> saveWork(@RequestBody WorkGroup workGroup) {
        return Result.success(workGroupService.create(workGroup));
    }

    /**
     * 根据用户Id查询动态分组
     */
    @GetMapping("/byUserId")
    public Result<List<WorkGroupDTO>> getWorkGroupByUserId(@RequestParam Long creatorId) {
        List<WorkGroupDTO> workGroupByUserId = workGroupService.getWorkGroupByUserId(creatorId);
        return Result.success(workGroupByUserId);
    }

    /**
     * 根据用户Id查询动态分组
     */
    @PutMapping("/updateWork")
    public Result<Boolean> updateWorkById(@RequestBody WorkGroup workGroup) {
        Boolean byId = workGroupService.updateWorkById(workGroup);
        return Result.success(byId);
    }


    @PostMapping("/addMember")
    public Result<Boolean> GroupAddMember(@RequestBody GroupAddMemberDTO groupAddMemberDTO) {
        return Result.success(workGroupService.GroupAddMember(groupAddMemberDTO));
    }

    // 删除动态分组
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteGroupById(@PathVariable("id") Long id) {
        return Result.success(workGroupService.deleteGroupById(id));
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public Result<Boolean> removeMemberById(
            @PathVariable("groupId") Long groupId,
            @PathVariable("userId") Long userId) {


        boolean success = workGroupService.removeMemberFromGroup(groupId, userId);
        return Result.success(success);
    }
}
