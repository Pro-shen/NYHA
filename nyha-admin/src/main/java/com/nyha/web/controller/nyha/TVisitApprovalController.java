package com.nyha.web.controller.nyha;

import com.nyha.common.annotation.Log;
import com.nyha.common.core.controller.BaseController;
import com.nyha.common.core.domain.AjaxResult;
import com.nyha.common.core.page.TableDataInfo;
import com.nyha.common.enums.BusinessType;
import com.nyha.common.utils.poi.ExcelUtil;
import com.nyha.system.domain.TVisit;
import com.nyha.system.service.ITVisitApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/nyha/tVisitApproval")
public class TVisitApprovalController extends BaseController {

    @Autowired
    private ITVisitApprovalService itVisitApprovalService;

    @PreAuthorize("@ss.hasPermi('nyha:tVisitApproval:list')")
    @GetMapping("/list")
    @Log(title = "就诊查询", businessType = BusinessType.OTHER)
    public TableDataInfo list(TVisit tVisit){
        startPage();
        List<TVisit> tVisits = itVisitApprovalService.list(tVisit);
        return getDataTable(tVisits);
    }

    @PreAuthorize("@ss.hasPermi('nyha:tVisitApproval:list')")
    @GetMapping("/getUserId/{userName}")
    @Log(title = "就诊查询", businessType = BusinessType.OTHER)
    public AjaxResult getUserId(@PathVariable("userName") String userName){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data",itVisitApprovalService.getUserId(userName));
        return ajaxResult;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitApproval:add')")
    @Log(title = "就诊增加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TVisit tVisit){
        AjaxResult ajax = new AjaxResult();
        ajax.put("data",itVisitApprovalService.add(tVisit));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitApproval:edit')")
    @GetMapping("/selectTVisitListById/{id}")
    public AjaxResult selectTVisitListById(@PathVariable("id") Long id){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itVisitApprovalService.selectTVisitListById(id));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitApproval:edit')")
    @Log(title = "就诊修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody TVisit tVisit){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itVisitApprovalService.edit(tVisit));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitApproval:remove')")
    @Log(title = "就诊删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody TVisit tVisit){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itVisitApprovalService.remove(tVisit));
        return ajax;
    }

    @Log(title = "就诊表单", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('nyha:tVisitRequest:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, TVisit tVisit)
    {
        List<TVisit> list = itVisitApprovalService.list(tVisit);
        ExcelUtil<TVisit> util = new ExcelUtil<TVisit>(TVisit.class);
        util.exportExcel(response, list, "菜单表单");
    }

}
