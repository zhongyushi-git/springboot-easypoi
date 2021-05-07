package com.example.easypoidemoadmin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author zhongyushi
 * @date 2020/6/26 0026
 * @dec 描述
 */
@Data
public class Company {
    @Excel(name = "公司名称",width =20)
    private String name;

    /**
     * type为 2 表示字段类型为图片
     * imageType为 1 表示从file读取
     */
    @Excel(name = "公司logo",width =20,type = 2,imageType = 1)
    private String logo;

    @Excel(name = "公司介绍",width =100)
    private String dec;

    public Company(String name,String logo,String dec){
        this.name=name;
        this.logo=logo;
        this.dec=dec;
    }

    public Company(){}
}
