package com.nyha.web.controller.nyha;

import com.nyha.common.annotation.Log;
import com.nyha.common.core.controller.BaseController;
import com.nyha.common.core.domain.AjaxResult;
import com.nyha.common.core.page.TableDataInfo;
import com.nyha.common.enums.BusinessType;
import com.nyha.common.utils.poi.ExcelUtil;
import com.nyha.system.domain.TFollow;
import com.nyha.system.service.ITFollowRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/nyha/tFollowRequest")
public class TFollowRequestController extends BaseController {

    @Autowired
    private ITFollowRequestService itFollowRequestService;

    @PreAuthorize("@ss.hasPermi('nyha:tFollowRequest:list')")
    @GetMapping("/list")
    @Log(title = "寻访查询", businessType = BusinessType.OTHER)
    public TableDataInfo list(TFollow tFollow){
        startPage();
        List<TFollow> tFollows = itFollowRequestService.list(tFollow);
        return getDataTable(tFollows);
    }

    @PreAuthorize("@ss.hasPermi('nyha:tFollowRequest:list')")
    @GetMapping("/getUserId/{userName}")
    @Log(title = "寻访查询", businessType = BusinessType.OTHER)
    public AjaxResult getUserId(@PathVariable("userName") String userName){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data",itFollowRequestService.getUserId(userName));
        return ajaxResult;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowRequest:add')")
    @Log(title = "寻访增加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TFollow tFollow){
        AjaxResult ajax = new AjaxResult();
        ajax.put("data",itFollowRequestService.add(tFollow));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowRequest:edit')")
    @Log(title = "寻访修改", businessType = BusinessType.UPDATE)
    @GetMapping("/selectTFollowListById/{id}")
    public AjaxResult selectTFollowListById(@PathVariable("id") Long id){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itFollowRequestService.selectTVisitListById(id));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowRequest:edit')")
    @Log(title = "寻访修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody TFollow tFollow){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itFollowRequestService.edit(tFollow));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowRequest:remove')")
    @Log(title = "寻访删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody TFollow tFollow){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itFollowRequestService.remove(tFollow));
        return ajax;
    }

    @Log(title = "寻访表单", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('nyha:tFollowRequest:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, TFollow tFollow)
    {
        List<TFollow> list = itFollowRequestService.list(tFollow);
        ExcelUtil<TFollow> util = new ExcelUtil<TFollow>(TFollow.class);
        util.exportExcel(response, list, "寻访表单");
    }
}
