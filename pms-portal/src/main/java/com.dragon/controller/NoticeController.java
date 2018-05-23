package com.dragon.controller;

import com.dragon.common.DataList;
import com.dragon.common.PMSResult;
import com.dragon.common.model.Notice;
import com.dragon.pojo.Tnotice;
import com.dragon.service.NoticeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @ApiOperation(value = "获取通告列表", notes = "根据分页来获取通告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "查询起始日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "查询结束日期", dataType = "String", paramType = "query")
    })
    @GetMapping()
    public PMSResult listNotices(Notice noticeInf) {

        DataList list = null;
        try {
            list = service.getNoticeByArgs(noticeInf);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }

        return PMSResult.Ok(list, "success");
    }

    @ApiOperation(value = "获取通告内容", notes = "根据来获取通告内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeid", value = "notice的id", dataType = "String", paramType = "query")
    })
    @GetMapping("/content")
    public PMSResult getNoticeContentById(Integer noticeId) {
        Tnotice tnotice = null;
        try {
            tnotice = service.getNoticeContent(noticeId);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }

        return PMSResult.Ok(tnotice, "获取成功");
    }

    @ApiOperation(value = "添加或修改通告内容", notes = "根据notice来添加或修改通告内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "notice", value = "notice的属性", dataType = "String", paramType = "query")
    })
    @PostMapping()
    @RequiresPermissions({"notice:add:all", "notice:update:all"})
    public PMSResult updateNotice(Tnotice notice) {
        Integer num = null;
        try {
            num = service.updateNotice(notice);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }
        return PMSResult.Ok(num, "操作成功");
    }

}

