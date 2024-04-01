package com.nyha.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nyha.common.annotation.Excel;
import com.nyha.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

public class TFollow extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 患者id
     */
    private Long patientId;

    /**
     * 患者名字
     */
    @Excel(name = "患者名称")
    private String patientName;

    /**
     * 医生id
     */
    private Long doctorId;

    /**
     * 医生名字
     */
    @Excel(name = "医生名称")
    private String doctorName;

    /**
     * 时间
     */
    @Excel(name = "寻访日期")
    @JsonFormat( pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai" )
    private Date date;

    /**
     * 就诊id
     */
    private Long visitId;

    /**
     * is_state
     */
    private Integer isState;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat( pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai" )
    private Date createTime;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat( pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai" )
    private Date updateTime;

    /**
     * 更新人id
     */
    private Long updateId;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 随访状态
     */
    private Integer followStatus;

    /**
     * ids
     */
    private List<Long> ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Integer getIsState() {
        return isState;
    }

    public void setIsState(Integer isState) {
        this.isState = isState;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
