package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SysRole;
import com.company.project.service.SysRoleService;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by CodeGenerator on 2021/04/20.
*/
@RestController
@RequestMapping("/sys/role")
@Api(tags = {"/sys/role"}, description = "角色管理模块")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody SysRole sysRole) {
        sysRole.setCreatedAt(new Date());
        sysRole.setIsDelete(false);
        sysRoleService.save(sysRole);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(sysRole);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        SysRole sysRole=new SysRole();
        sysRole.setId(id);
        sysRole.setIsDelete(true);
        sysRoleService.update(sysRole);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody SysRole sysRole) {
        sysRole.setUpdatedAt(new Date());
        sysRoleService.update(sysRole);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(sysRole);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        SysRole sysRole = sysRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysRole);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) SysRole sysRole) {
        PageHelper.startPage(sysRole.getPage(), sysRole.getLimit());
        sysRole.setIsDelete(false);
        List<SysRole> list = sysRoleService.findByModel(sysRole);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
