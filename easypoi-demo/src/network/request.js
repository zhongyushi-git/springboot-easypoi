import axios from 'axios'
// import store from 'store/index'
//设置全局配置
axios.defaults.baseURL = '/easypoidemo-admin/api'

axios.defaults.timeout = 5000
//设置post请求的格式
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
//设置请求头的token
// axios.defaults.headers.common["token"] = sessionStorage.getItem("token")



const request = {
    get(url, params) {
        return new Promise((resolve, reject) => {
            axios.get(url, {
                params: params
            }).then(res => {
                resolve(res.data);
            }).catch(err => {
                reject(err.data)
            })
        });
    },
    post(url, params) {
        return new Promise((resolve, reject) => {
            axios.post(url, params)
                .then(res => {
                    resolve(res.data);
                })
                .catch(err => {
                    reject(err.data)
                })
        });
    },
    put(url, params) {
        return new Promise((resolve, reject) => {
            axios.put(url, params)
                .then(res => {
                    resolve(res.data);
                })
                .catch(err => {
                    reject(err.data)
                })
        });
    },
    delete(url) {
        return new Promise((resolve, reject) => {
            axios.delete(url)
                .then(res => {
                    resolve(res.data);
                })
                .catch(err => {
                    reject(err.data)
                })
        });
    },
    postResponse(url, params) {
        return new Promise((resolve, reject) => {
            axios.request({
                url: url,
                method: 'POST',
                data: params,
                responseType: 'arraybuffer'
            })
                .then(res => {
                    resolve(res.data);
                })
                .catch(err => {
                    reject(err.data)
                })
        });
    },
}

//响应拦截器
axios.interceptors.response.use(
    response => {
        const headers = response.headers
        // 此类为下载流文件，不拦截
        if (headers['content-type'] === 'application/octet-stream;charset=utf-8') {
            return response
        }
        if (headers['content-type'] === 'arrayBuffer;charset=UTF-8') {
            return response
        }
        if (headers['content-type'] === 'application/vnd.ms-excel;charset=UTF-8') {
            return response
        }

        if (response.request.responseType === 'arraybuffer') {
            const enc = new TextDecoder('utf-8')
            response.data = JSON.parse(enc.decode(new Uint8Array(res)))
        }
        return response


    },
    error => {
        console.log('err' + error) // for debug
    }
)

export default request