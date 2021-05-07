package com.example.easypoidemoadmin.entity;

import lombok.Data;

/**
 * @Aauthor yushizhong
 * @Date 2020/5/8 15:22
 * @Dec 数据返回结果对象
 */
@Data
public class CommonResult {
    private Integer code;//状态编码
    private String msg;//结果信息
    private long total;//数据条数
    private Object data;//数据

    public CommonResult(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public CommonResult(Integer code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public CommonResult(Integer code,String msg,Object data,long total){
        this.code=code;
        this.msg=msg;
        this.data=data;
        this.total=total;
    }
}
