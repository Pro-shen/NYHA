package com.nyha.web.controller.nyha;

import com.nyha.common.annotation.Log;
import com.nyha.common.core.controller.BaseController;
import com.nyha.common.core.domain.AjaxResult;
import com.nyha.common.core.page.TableDataInfo;
import com.nyha.common.enums.BusinessType;
import com.nyha.common.utils.poi.ExcelUtil;
import com.nyha.system.domain.TFollow;
import com.nyha.system.service.ITFollowApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/nyha/tFollowApproval")
public class TFollowApprovalController extends BaseController {

    @Autowired
    private ITFollowApprovalService itFollowApprovalService;

    @PreAuthorize("@ss.hasPermi('nyha:tFollowApproval:list')")
    @GetMapping("/list")
    @Log(title = "寻访查询", businessType = BusinessType.OTHER)
    public TableDataInfo list(TFollow tFollow){
        startPage();
        List<TFollow> tFollows = itFollowApprovalService.list(tFollow);
        return getDataTable(tFollows);
    }

    @PreAuthorize("@ss.hasPermi('nyha:tFollowApproval:list')")
    @GetMapping("/getUserId/{userName}")
    @Log(title = "寻访查询", businessType = BusinessType.OTHER)
    public AjaxResult getUserId(@PathVariable("userName") String userName){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data",itFollowApprovalService.getUserId(userName));
        return ajaxResult;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowApproval:add')")
    @Log(title = "寻访增加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TFollow tFollow){
        AjaxResult ajax = new AjaxResult();
        ajax.put("data",itFollowApprovalService.add(tFollow));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowApproval:edit')")
    @Log(title = "寻访修改", businessType = BusinessType.UPDATE)
    @GetMapping("/selectTFollowListById/{id}")
    public AjaxResult selectTFollowListById(@PathVariable("id") Long id){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itFollowApprovalService.selectTVisitListById(id));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowApproval:edit')")
    @Log(title = "寻访修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody TFollow tFollow){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itFollowApprovalService.edit(tFollow));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tFollowApproval:remove')")
    @Log(title = "寻访删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody TFollow tFollow){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itFollowApprovalService.remove(tFollow));
        return ajax;
    }

    @Log(title = "寻访表单", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('nyha:tFollowApproval:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, TFollow tFollow)
    {
        List<TFollow> list = itFollowApprovalService.list(tFollow);
        ExcelUtil<TFollow> util = new ExcelUtil<TFollow>(TFollow.class);
        util.exportExcel(response, list, "寻访表单");
    }

}
