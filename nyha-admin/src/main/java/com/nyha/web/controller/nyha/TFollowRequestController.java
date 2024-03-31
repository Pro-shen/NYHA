package com.nyha.web.controller.nyha;

import com.nyha.common.annotation.Log;
import com.nyha.common.core.controller.BaseController;
import com.nyha.common.core.domain.AjaxResult;
import com.nyha.common.core.page.TableDataInfo;
import com.nyha.common.enums.BusinessType;
import com.nyha.system.domain.TFollow;
import com.nyha.system.domain.TVisit;
import com.nyha.system.service.ITFollowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nyha/tFollowRequest")
public class TFollowRequestController extends BaseController {

    @Autowired
    private ITFollowRequestService itFollowRequestService;

    @PreAuthorize("@ss.hasPermi('nyha:tFollowRequest:list')")
    @GetMapping("/list")
    @Log(title = "就诊查询", businessType = BusinessType.OTHER)
    public TableDataInfo list(TFollow tFollow){
        startPage();
        List<TFollow> tFollows = itFollowRequestService.list(tFollow);
        return getDataTable(tFollows);
    }

    @PreAuthorize("@ss.hasPermi('nyha:tFollowRequest:list')")
    @GetMapping("/getUserId/{userName}")
    @Log(title = "就诊查询", businessType = BusinessType.OTHER)
    public AjaxResult getUserId(@PathVariable("userName") String userName){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data",itFollowRequestService.getUserId(userName));
        return ajaxResult;
    }

}
