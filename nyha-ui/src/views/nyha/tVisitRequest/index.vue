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
          v-hasPermi="['nyha:tVisitRequest:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['nyha:tVisitRequest:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['nyha:tVisitRequest:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['nyha:tVisitRequest:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="tVisitList" row-key="id" :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="patientName" label="就诊人" width="100"></el-table-column>
      <el-table-column label="就诊状态" width="170" align="center">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.t_visit_visit_status" :value="scope.row.visitStatus" />
        </template>
      </el-table-column>
      <el-table-column prop="symptoms" label="症状体征" width="230"></el-table-column>
      <el-table-column prop="results" label="就诊结果" width="230"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="150"></el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['nyha:tVisitRequest:edit']">修改</el-button>
          <el-button v-if="scope.row.parentId != 0" size="mini" type="text" icon="el-icon-delete"
            @click="handleDelete(scope.row)" v-hasPermi="['nyha:tVisitRequest:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改菜单配置对话框 -->
    <el-dialog title="就诊请求" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="症状体征" prop="symptoms">
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
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list, getUserId, add, selectTVisitListById, edit, remove } from "@/api/nyha/tVisitRequest"
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
      tVisitList: [],
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
        this.tVisitList = response.rows
        this.loading = false
        this.total = response.total
      })
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.symptomsList = selection.map(item => item.symptoms)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.open = true
      this.add = true
      this.reset()
    },
    handleUpdate(row) {
      this.add = false
      this.reset()
      this.open = true
      const id = row.id || this.ids
      this.form.id = id
      selectTVisitListById(id).then(res => {
        this.form = res.data[0]
      })
    },
    submitForm() {
      this.form.isState = 1
      this.form.patientId = this.userId
      this.form.patientName = this.userName
      this.form.visitStatus = 0
      this.open = false
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.add) {
            add(this.form).then(res => {
              if (res.data == 0) {
                this.$modal.msgError("添加失败")
              } else if (res.data == 1) {
                this.$modal.msgSuccess("添加成功")
                this.getList();
              }
            })
          } else {
            edit(this.form).then(res => {
              if (res.data == 0) {
                this.$modal.msgError("修改失败")
              } else if (res.data == 1) {
                this.$modal.msgSuccess("修改成功")
                this.getList();
              }
            })
          }
        }
      })
    },
    cancel() {
      this.open = false
    },
    handleDelete(row) {
      var that = this
      const ids = row.id || this.ids
      const symptomsList = row.symptoms || this.symptomsList
      if (row.id == 0) {
        ids.push(0)
      }
      var str = ""
      var listOrInt = 1
      for (var i = 0; i < ids.length; i++) {
        if (i == ids.length - 1) {
          str = str + symptomsList[i]
        } else {
          str = str + symptomsList[i] + ","
        }
      }
      if (str == "") {
        listOrInt = 0
        str = symptomsList
      }
      this.$modal.confirm('是否确认删除症状为"' + str + '"的数据项？').then(function () {
        var data = {}
        if (listOrInt == 0) {
          data = {
            id: ids
          }
        } else {
          data = {
            ids: ids
          }
        }
        remove(data).then(res => {
          if (res.data.ids.length == 0) {
            that.$modal.msgSuccess("删除成功")
          } else {
            var resStr = ""
            for (i = 0; i < res.data.ids.length; i++) {
              resStr = resStr + res.data.ids[i].symptoms + ",";
            }
            that.$modal.msgError("删除" + resStr + "失败")
          }
          that.getList()
        })
      })
    },
    handleExport() {
      this.download('/nyha/tVisitRequest/export', {
        ...this.queryParams
      }, `就诊_${new Date().getTime()}.xlsx`)
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.queryParams.pageNum = 1;
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order)
    },
    reset() {
      this.form = {
        symptoms: undefined,
        compliance: undefined,
        psychology: undefined,
        lifeStyle: undefined,
        isState: undefined
      };
      this.resetForm("form");
    }
  }

}
</script>

<style></style>