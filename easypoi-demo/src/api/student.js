import request from 'network/request'



//把接口放在一起，方便接口统一管理
export const student = {

   /**
    * 查询学生信息
    * @param {*} query 
    */
   getList(query) {
      return request.get('student/list', query)
   },

   /**
    * 导入学生信息
    * @param {*} file 
    */
   upload(file) {
      return request.postResponse('student/upload', file)
   },

   /**
   * 下载模板
   * @param {*} file 
   */
   exportTemplate(data) {
      return request.postResponse('student/exportTemplate', data)
   }

}