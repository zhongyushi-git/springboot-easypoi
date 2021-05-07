module.exports = {
  configureWebpack: {
    resolve: {
      //设置别名
      alias: {
        'assets': '@/assets',
        'components': '@/components',
        'network': '@/network',
        'views': '@/views',
        'api':'@/api',
      }
    }
  },
  devServer: {
    host: 'localhost',
    // //设置端口号
    port: 8002,
    //设置跨域
    proxy: {
      //后端的项目名称
      '/easypoidemo-admin': {
        target: 'http://localhost:8001', //设置调用的接口域名和端口号
        changeOrigin: true, //虚拟的站点需要更管origin
      }
    }
  },
  
}
