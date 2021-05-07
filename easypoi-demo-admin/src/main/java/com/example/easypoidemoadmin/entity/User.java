package com.example.easypoidemoadmin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhongyushi
 * @date 2020/6/24 0024
 * @dec 用户实体
 */
@Data
@TableName(value = "User")
public class User {

    /**
     * 用户名
     */
    @TableId(value = "username")
    @Excel(name = "用户名", orderNum = "0", width = 30)
    private String username;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @Excel(name = "姓名", orderNum = "1", width = 30)
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @Excel(name = "年龄", orderNum = "2")
    private Integer age;

    /**
     * 性别,0表示男，1表示女
     */
    @TableField(value = "sex")
    @Excel(name = "性别", orderNum = "3", replace = {"男_0", "女_1"})
    private String sex;

    /**
     * 籍贯
     */
    @TableField(value = "address")
    @Excel(name = "籍贯", orderNum = "4", width = 30)
    private String address;

}
