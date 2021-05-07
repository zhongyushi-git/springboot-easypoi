//element-ui 分页组件封装
<template>
  <el-pagination
    background
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="pageData.page"
    :page-sizes="pageSizes"
    :page-size="pageData.limit"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pageData.total"
  ></el-pagination>
</template>

<script>
export default {
  name: "Pagination",
  data(){
      return{
          pageSizes: [5,10, 20, 50, 100],
          page:1,
          limit:10
      }
  },
  props: {
    pageData: {
      type: Object,
      default() {
        return {
          total: 0,
          page:1,
          limit:10
        };
      }
    }
  },
  methods: {
    //当每页显示条数改变
    handleSizeChange(val) {
      this.pageData.limit =val
      this.$emit("pageChange",this.pageData.page,this.pageData.limit)
    },
    //页面发送改变
    handleCurrentChange(val) {
      this.pageData.page=val
      console.log(this.pageData.page,this.pageData.limit)
       this.$emit("pageChange",this.pageData.page,this.pageData.limit)
    }
  }
};
</script>

<style scoped>
</style>