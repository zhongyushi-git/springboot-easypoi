<template>
  <div>
    <div class="app-container">
      <el-form :inline="true">
        <el-form-item label="姓名:">
          <el-input v-model="pageData.name" size="small" placeholder="请输入姓名" clearable></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-button type="primary" icon="el-icon-search" size="small" @click="getList">查询 </el-button>
          <el-upload class="upload-demo" action="" :limit="1" :http-request="importExcel" :show-file-list="false"
            :file-list="fileList">
            <el-button size="small" :loading="importLoading" type="warning" icon="el-icon-upload">导入学生信息</el-button>
          </el-upload>
          <el-button type="success" icon="el-icon-download" size="small" @click="exportTemplate">导出模板</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row
        size="small">
        <el-table-column label="姓名" align="center">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
        <el-table-column label="年龄" align="center">
          <template slot-scope="scope">{{ scope.row.age }}</template>
        </el-table-column>
        <el-table-column label="出生日期" align="center">
          <template slot-scope="scope">{{ scope.row.birth }}</template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="pageData.total>0" :pageData="pageData" @pageChange="pageChange"></pagination>
  </div>
</template>

<script>
  import Pagination from 'components/Pagination'
  import { student } from 'api/student'
  import fileDownload from "js-file-download"
  export default {
    components: { Pagination },
    created() {
      this.getList()
    },
    data() {
      return {
        list: null,
        listLoading: false,
        importLoading: false,
        pageData: {
          total: 0,
          page: 1,
          limit: 10,
          name: '',
        },
        fileList: [],
      }
    },
    methods: {
      getList() {
        this.listLoading = true
        student.getList(this.pageData).then(res => {
          this.list = res.data
          this.pageData.total = res.total
          this.listLoading = false
        }).catch(err => {
          console.log(err)
        })
      },
      //页码改变
      pageChange(curr, size) {
        this.pageData.page = curr
        this.pageData.limit = size
        this.getList()
      },
      //导入
      importExcel(param) {
        const file = param.file
        if (file.name.lastIndexOf('.') < 0) {
          this.$message.error('上传文件只能是xls、xlsx格式!')
          return
        }
        const testMsg = file.name.substring(file.name.lastIndexOf('.') + 1).toLowerCase()
        const extensionXLS = testMsg == 'xls'
        const extensionXLSX = testMsg == 'xlsx'
        if (!extensionXLS && !extensionXLSX) {
          this.$message.error('上传文件只能是xls、xlsx格式!')
          return
        }
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isLt2M) {
          this.$message.error('上传文件不能超过 2MB!')
          return
        }
        this.importLoading = true
        const formData = new FormData()
        formData.append('file', param.file)
        student.upload(formData).then(res => {
          if (!res.code) {
            this.$message.error("部分数据导入失败，数据已下载到本地，请查看！")
            fileDownload(res, '导入异常的数据.xlsx')
            this.fileList = []
            this.getList()
          } else if (res.code == 200) {
            this.$message.success("导入成功")
            this.fileList = []
            this.getList()
          } else {
            this.$message.error("导入失败")
          }
        }).catch(err => {
          console.log(err)
          this.$message.error("导入失败")
        }).finally(()=>{
          this.importLoading = false
        })
      },
      //下载模板
      exportTemplate() {
        const fileName = '学生信息'
        student.exportTemplate({
          title: fileName,
          sheetName: fileName,
          fileName: fileName,
        }).then(res => {
          fileDownload(res, fileName + '导入模板.xlsx')
        }, err => {
          console.log('下载出错了')
        })
      }

    },
  }
</script>

<style scoped>
  .app-container {
    text-align: center;
    padding-bottom: 0px;
  }

  .upload-demo {
    display: inline-block;
    padding-left: 10px;
    padding-right: 10px;
  }
</style>