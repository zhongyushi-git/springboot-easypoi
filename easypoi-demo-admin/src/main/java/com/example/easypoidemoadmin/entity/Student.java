package com.example.easypoidemoadmin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@TableName(value = "student")
public class Student implements IExcelModel {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @Excel(name = "姓名", width = 20)
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 年龄验证
     */
    @TableField(exist = false)
    @Excel(name = "年龄")
    @NotNull(message = "年龄不能为空")
    @Pattern(regexp = "^(?:[1-9][0-9]?|1[01][0-9]|120)$", message = "年龄必须是整数，且在1-120之间")
    private String ageStr;

    /**
     * 出生日期
     */
    @TableField(value = "birth")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 出生日期验证
     */
    @TableField(exist = false)
    @Excel(name = "出生日期", isImportField = "true", importFormat = "yyyy-MM-dd", databaseFormat = "yyyy-MM-dd", width = 30)
    @NotNull(message = "出生日期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{1,2}-\\d{1,2}$", message = "日期格式必须是yyyy-MM-dd格式，如2020-01-01")
    private String birthStr;

    //错误信息
    @TableField(exist = false)
    @Excel(name = "错误信息", width = 50, isColumnHidden = true)
    private String errorMsg;


}
