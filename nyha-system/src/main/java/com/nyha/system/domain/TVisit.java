package com.nyha.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nyha.common.annotation.Excel;
import com.nyha.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

public class TVisit extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 就诊患者id
     */
    private Long patientId;

    /**
     * 就诊患者名字
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
     * 护士1id
     */
    private Long nurse1Id;

    /**
     * 护士1名字
     */
    @Excel(name = "护士1名称")
    private String nurse1Name;

    /**
     * 护士2id
     */
    private Long nurse2Id;

    /**
     * 护士2名字
     */
    @Excel(name = "护士2名称")
    private String nurse2Name;

    /**
     * 护士数量
     */
    private Long nurseNumber;

    /**
     * 就诊日期
     */
    @Excel(name = "就诊日期")
    private Date visitDate;

    /**
     * 就诊的症状体征
     */
    @Excel(name = "就诊的症状体征")
    private String symptoms;

    /**
     * 实验室检查结果
     */
    @Excel(name = "实验室检查结果")
    private String results;

    /**
     * 化验单图片路径
     */
    private String pictureUrl;

    /**
     * 用药的依从性
     */
    @Excel(name = "用药的依从性")
    private Integer compliance;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 最近有无诱因
     */
    @Excel(name = "最近有无诱因")
    private String incentive;

    /**
     * 有无再入院
     */
    @Excel(name = "有无再入院")
    private Integer inHospital;

    /**
     * 心理状态
     */
    @Excel(name = "心理状态")
    private String psychology;

    /**
     * 生活方式
     */
    @Excel(name = "生活方式")
    private String lifeStyle;

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
     * NYHA心功能分级评估
     */
    @Excel(name = "NYHA心功能分级评估")
    private String nyha;

    /**
     * 就诊状态
     */
    @Excel(name = "就诊状态")
    private Integer visitStatus;

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

    public Long getNurse1Id() {
        return nurse1Id;
    }

    public void setNurse1Id(Long nurse1Id) {
        this.nurse1Id = nurse1Id;
    }

    public String getNurse1Name() {
        return nurse1Name;
    }

    public void setNurse1Name(String nurse1Name) {
        this.nurse1Name = nurse1Name;
    }

    public Long getNurse2Id() {
        return nurse2Id;
    }

    public void setNurse2Id(Long nurse2Id) {
        this.nurse2Id = nurse2Id;
    }

    public String getNurse2Name() {
        return nurse2Name;
    }

    public void setNurse2Name(String nurse2Name) {
        this.nurse2Name = nurse2Name;
    }

    public Long getNurseNumber() {
        return nurseNumber;
    }

    public void setNurseNumber(Long nurseNumber) {
        this.nurseNumber = nurseNumber;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getCompliance() {
        return compliance;
    }

    public void setCompliance(Integer compliance) {
        this.compliance = compliance;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIncentive() {
        return incentive;
    }

    public void setIncentive(String incentive) {
        this.incentive = incentive;
    }

    public Integer getInHospital() {
        return inHospital;
    }

    public void setInHospital(Integer inHospital) {
        this.inHospital = inHospital;
    }

    public String getPsychology() {
        return psychology;
    }

    public void setPsychology(String psychology) {
        this.psychology = psychology;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
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

    public String getNyha() {
        return nyha;
    }

    public void setNyha(String nyha) {
        this.nyha = nyha;
    }

    public Integer getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(Integer visitStatus) {
        this.visitStatus = visitStatus;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
