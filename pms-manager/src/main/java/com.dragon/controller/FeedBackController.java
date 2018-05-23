package com.dragon.controller;

import com.dragon.common.DataList;
import com.dragon.common.PMSResult;
import com.dragon.common.model.FeedBack;
import com.dragon.pojo.Tfeedback;
import com.dragon.service.FeedBackService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {

    @Autowired
    private FeedBackService service;

    @ApiOperation(value = "获取反馈列表", notes = "根据分页来获取反馈列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "反馈标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "查询起始日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "查询结束日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "处理状态", dataType = "String", paramType = "query")
    })
    @GetMapping
    public PMSResult getFeedBackList(FeedBack feedBack) {
        DataList list = null;

        try {
            list = service.getFeedBacks(feedBack);
        } catch (Exception e) {
            e.printStackTrace();
            PMSResult.Fail();
        }

        return PMSResult.Ok(list, "success");
    }


    @ApiOperation(value = "获取反馈内容", notes = "根据来获取反馈内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedbackId", value = "feedback的id", dataType = "String", paramType = "query")
    })
    @GetMapping("/content")
    public PMSResult getNoticeContentById(Integer fId) {
        Tfeedback feedback = null;
        try {
            feedback = service.getFBContent(fId);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }

        return PMSResult.Ok(feedback, "获取成功");
    }


    @ApiOperation(value = "删除反馈", notes = "根据fIds删除反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fIds", value = "反馈id集合", dataType = "String", paramType = "query"),
    })
    @DeleteMapping("/{fIds}")
    @RequiresPermissions("feedback:del:all")
    public PMSResult delFeedback(
            @PathVariable(value = "fIds") String fIds) {
        Integer num = null;
        try {
            num = service.delFeedBacks(fIds);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }
        return PMSResult.Ok(num, "success");
    }


    @ApiOperation(value = "更新反馈状态为已完成", notes = "更新反馈状态为已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "feedbackId", value = "feedback的id", dataType = "String", paramType = "query")
    })
    @GetMapping("/handle")
    public PMSResult updateStatus(Integer fId) {
        Integer num = null;
        try {
            num = service.updateStatus(fId);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }
        return PMSResult.Ok(num, "success");
    }
}
