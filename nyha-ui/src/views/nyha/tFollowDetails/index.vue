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
          v-hasPermi="['nyha:tFollowDetails:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['nyha:tFollowDetails:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['nyha:tFollowDetails:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['nyha:tFollowDetails:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="tFollowList" row-key="id" :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="patientName" label="随访人" width="100"></el-table-column>
      <el-table-column prop="date" label="随访时间" width="100"></el-table-column>
      <el-table-column label="随访状态" width="100" align="center">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.t_follow_follow_status" :value="scope.row.followStatus" />
        </template>
      </el-table-column>
      <el-table-column prop="doctorName" label="医生名字" width="150"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="150"></el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['nyha:tFollowDetails:edit']">修改</el-button>
          <el-button v-if="scope.row.parentId != 0" size="mini" type="text" icon="el-icon-delete"
            @click="handleDelete(scope.row)" v-hasPermi="['nyha:tFollowDetails:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改菜单配置对话框 -->
    <el-dialog title="就诊请求" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="请求日期" prop="date">
          <el-date-picker v-model="form.date" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
          </el-date-picker>
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
import { list, getUserId, add, selectTFollowListById, edit, remove } from "@/api/nyha/tFollowDetails"
import Cookies from "js-cookie";
export default {
  dicts: ['t_follow_follow_status'],
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
      dateList: [],
      patientNameList: [],
      rules: {
        date: [
          { required: true, message: "请求日期不能为空", trigger: "blur" }
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.dateList = selection.map(item => item.date)
      this.patientNameList = selection.map(item => item.patientName)
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
      selectTFollowListById(id).then(res => {
        this.form = res.data[0]
      })
    },
    submitForm() {
      this.form.isState = 1
      this.form.patientId = this.userId
      this.form.patientName = this.userName
      this.form.followStatus = 0
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
      const dateList = row.date || this.dateList
      const patientNameList = row.patientName || this.patientNameList
      if (row.id == 0) {
        ids.push(0)
      }
      var str = ""
      var listOrInt = 1
      for (var i = 0; i < ids.length; i++) {
        if (i == ids.length - 1) {
          str = str + "日期为:" + dateList[i] + "访客为:" + patientNameList[i]
        } else {
          str = str + "日期为:" + dateList[i] + "访客为:" + patientNameList[i] + ","
        }
      }
      if (str == "") {
        listOrInt = 0
        str = "日期为:" + dateList + "访客为:" + patientNameList
      }
      this.$modal.confirm('是否确认删除"' + str + '"的数据项？').then(function () {
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
      this.download('/nyha/tFollowDetails/export', {
        ...this.queryParams
      }, `寻访_${new Date().getTime()}.xlsx`)
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