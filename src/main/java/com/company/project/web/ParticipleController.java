package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Participle;
import com.company.project.service.ParticipleService;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* Created by CodeGenerator on 2021/05/15.
*/
@RestController
@RequestMapping("/participle")
@Api(tags = {"/participle"}, description = "分词管理模块")
public class ParticipleController {
    @Resource
    private ParticipleService participleService;

    @ApiOperation(value = "新增分词", notes = "新增分词")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Participle participle) {
        participle.setCreatedAt(new Date());
        participle.setIsDelete(false);
        participleService.save(participle);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(participle);
        return result;
    }

    @ApiOperation(value = "删除分词", notes = "删除分词")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Participle participle=new Participle();
        participle.setId(id);
        participle.setIsDelete(true);
        participleService.update(participle);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改分词", notes = "修改分词")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Participle participle) {
        participle.setUpdatedAt(new Date());
        participleService.update(participle);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(participle);
        return result;
    }

    @ApiOperation(value = "获取分词单个详情", notes = "获取分词单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        Participle participle = participleService.findById(id);
        return ResultGenerator.genSuccessResult(participle);
    }

    @ApiOperation(value = "分页查询分词", notes = "分页查询分词")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Participle participle) {
        PageHelper.startPage(participle.getPage(), participle.getLimit());
        participle.setIsDelete(false);
        List<Participle> list = participleService.findByModel(participle);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
