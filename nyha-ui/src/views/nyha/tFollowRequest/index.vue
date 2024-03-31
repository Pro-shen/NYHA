<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="请求时间">
        <el-date-picker v-model="dateRange" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
          range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['nyha:tFollowRequest:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['nyha:tFollowRequest:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['nyha:tFollowRequest:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['nyha:tFollowRequest:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="tFollowList" row-key="id" :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="patientName" label="随访人" width="100"></el-table-column>
      <el-table-column label="随访状态" width="170" align="center">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.t_visit_visit_status" :value="scope.row.visitStatus" />
        </template>
      </el-table-column>
      <el-table-column prop="doctorName" label="医生名字" width="230"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="150"></el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['nyha:tFollowRequest:edit']">修改</el-button>
          <el-button v-if="scope.row.parentId != 0" size="mini" type="text" icon="el-icon-delete"
            @click="handleDelete(scope.row)" v-hasPermi="['nyha:tFollowRequest:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改菜单配置对话框 -->
    <el-dialog title="就诊请求" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <!-- <el-form-item label="症状体征" prop="symptoms">
          <el-input v-model="form.symptoms" placeholder="请输入症状体征" />
        </el-form-item>
        <el-form-item label="用药情况" prop="compliance">
          <el-input v-model="form.compliance" placeholder="请输入用药情况" />
        </el-form-item>
        <el-form-item label="心理状态" prop="psychology">
          <el-input v-model="form.psychology" placeholder="请输入心理状态" />
        </el-form-item>
        <el-form-item label="生活方式" prop="lifeStyle">
          <el-input v-model="form.lifeStyle" placeholder="请输入生活方式" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { list, getUserId, add, selectTFollowListById, edit, remove } from "@/api/nyha/tFollowRequest"
import Cookies from "js-cookie";
export default {
  dicts: ['t_visit_visit_status'],
  data() {
    return {
      userName: undefined,
      userId: undefined,
      dateRange: [],
      queryParams: {
        tenantId: 1,
        pageNum: 1,
        pageSize: 10,
        createTime: undefined
      },
      form: {},
      showSearch: true,
      tFollowList: [],
      total: 0,
      add: false,
      single: true,
      multiple: true,
      loading: true,
      open: false,
      refreshTable: true,
      isExpandAll: true,
      ids: [],
      symptomsList: [],
      rules: {
        symptoms: [
          { required: true, message: "症状体征不能为空", trigger: "blur" }
        ],
        compliance: [
          { required: true, message: "用药情况不能为空", trigger: "blur" }
        ],
        psychology: [
          { required: true, message: "心理状态不能为空", trigger: "blur" }
        ],
        lifeStyle: [
          { required: true, message: "生活方式不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getUserInfo()
    this.getList()
  },
  methods: {
    getUserInfo() {
      this.userName = Cookies.get("username")
      getUserId(this.userName).then(res => {
        this.userId = res.data.userId
      })
    },
    getList() {
      this.loading = true;
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.tFollowList = response.rows
        this.loading = false
        this.total = response.total
      })
    },
    handleSelectionChange(selection){
      
    },
    handleAdd(){

    },
    handleUpdate(row){

    },
    submitForm(){

    },
    cancel(){

    },
    handleDelete(row){

    },
    handleExport(){

    },
    handleQuery(){

    },
    resetQuery(){

    },
    reset(){

    }
  }
}
</script>

<style></style>