package com.nyha.web.controller.nyha;

import com.nyha.common.annotation.Log;
import com.nyha.common.core.controller.BaseController;
import com.nyha.common.core.domain.AjaxResult;
import com.nyha.common.core.page.TableDataInfo;
import com.nyha.common.enums.BusinessType;
import com.nyha.common.utils.poi.ExcelUtil;
import com.nyha.system.domain.TVisit;
import com.nyha.system.service.ITVisitRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/nyha/tVisitRequest")
public class TVisitRequestController extends BaseController {

    @Autowired
    private ITVisitRequestService itVisitRequestService;

    @PreAuthorize("@ss.hasPermi('nyha:tVisitRequest:list')")
    @GetMapping("/list")
    @Log(title = "就诊查询", businessType = BusinessType.OTHER)
    public TableDataInfo list(TVisit tVisit){
        startPage();
        List<TVisit> tVisits = itVisitRequestService.list(tVisit);
        return getDataTable(tVisits);
    }

    @PreAuthorize("@ss.hasPermi('nyha:tVisitRequest:list')")
    @GetMapping("/getUserId/{userName}")
    @Log(title = "就诊查询", businessType = BusinessType.OTHER)
    public AjaxResult getUserId(@PathVariable("userName") String userName){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data",itVisitRequestService.getUserId(userName));
        return ajaxResult;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitRequest:add')")
    @Log(title = "就诊增加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody TVisit tVisit){
        AjaxResult ajax = new AjaxResult();
        ajax.put("data",itVisitRequestService.add(tVisit));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitRequest:edit')")
    @GetMapping("/selectTVisitListById/{id}")
    public AjaxResult selectTVisitListById(@PathVariable("id") Long id){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itVisitRequestService.selectTVisitListById(id));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitRequest:edit')")
    @Log(title = "就诊修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody TVisit tVisit){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itVisitRequestService.edit(tVisit));
        return ajax;
    }

    @PreAuthorize("@ss.hasAnyPermi('nyha:tVisitRequest:remove')")
    @Log(title = "就诊删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody TVisit tVisit){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data",itVisitRequestService.remove(tVisit));
        return ajax;
    }

    @Log(title = "就诊表单", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('nyha:tVisitRequest:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, TVisit tVisit)
    {
        List<TVisit> list = itVisitRequestService.list(tVisit);
        ExcelUtil<TVisit> util = new ExcelUtil<TVisit>(TVisit.class);
        util.exportExcel(response, list, "就诊表单");
    }

}
