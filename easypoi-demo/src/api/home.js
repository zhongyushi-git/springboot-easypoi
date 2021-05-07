import request from 'network/request'



//把接口放在一起，方便接口统一管理
export const home = {


   /**
    * 查询用户信息
    * @param {*} query 
    */
   getList(query) {
      return request.get('user/list', query)
   },

   /**
    * 导出用户信息
    */
   exportExcel(query) {
      return request.postResponse('user/exportExcel', query)
   },

   /**
    * 导入用户信息
    * @param {*} file 
    */
   upload(file) {
      return request.post('user/import', file)
   },

   /**
    * 导入excel图片
    * @param {*} file 
    */
   upload2(file) {
      return request.post('user/imgimport', file)
   },

   /**
    * 使用模板导出用户信息
    */
   exportExcelByTemplate(query) {
      return request.postResponse('user/excelTemplate', query)
   },

   /**
    * 下载excel图片文件
    */
   exportImgExcel(query) {
      return request.postResponse('user/imgexport', query)
   },

   /**
    * excel预览
    */
   previewExcel() {
      return request.get('user/previewExcel')
   },

   /**
   * 使用模板导出word
   */
   exportWordByTemplate(query) {
      return request.postResponse('user/wordTemplate', query)
   },

   /**
   * 使用模板导出多页word
   */
  exportWordByTemplateMorePage(query) {
   return request.postResponse('user/wordTemplateMorePage', query)
},
}