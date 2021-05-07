package com.example.easypoidemoadmin.controller;


import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.easypoidemoadmin.entity.CommonResult;
import com.example.easypoidemoadmin.entity.Company;
import com.example.easypoidemoadmin.entity.User;
import com.example.easypoidemoadmin.service.UserService;
import com.example.easypoidemoadmin.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhongyushi
 * @date 2020/6/24 0024
 * @dec 用户接口
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息列表
     *
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    public CommonResult getList(String name, Integer page, Integer limit) {
        IPage<User> iPage = userService.getList(name, page, limit);
        return new CommonResult(200, "查询用户信息成功", iPage.getRecords(), iPage.getTotal());
    }

    /**
     * 导出数据，使用map接收
     *
     * @param map
     * @param response
     */
    @PostMapping("/exportExcel")
    public void exportExcel(@RequestBody Map<String, Object> map, HttpServletResponse response) {
        IPage<User> iPage = userService.getList((String) map.get("name"), (Integer) map.get("page"), (Integer) map.get("limit"));
        try {
            ExcelUtils.exportExcel(iPage.getRecords(), (String) map.get("title"), (String) map.get("sheetName"), User.class, (String) map.get("fileName"), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入数据
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public CommonResult importExcel(@RequestParam("file") MultipartFile file) {
        List<User> list = null;
        try {
            list = ExcelUtils.importExcel(file, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = userService.insertByBatch(list);
        if (i != 0) {
            return new CommonResult(200, "导入成功");
        } else {
            return new CommonResult(444, "导入失败");
        }
    }


    /**
     * 图片的导出
     *
     * @param response
     */
    @PostMapping("/imgexport")
    public void imgExport(HttpServletResponse response, @RequestBody Map<String, Object> map) {
        List<Company> list = new ArrayList<>();
        //图片的路径自定义，但必须要正确
        list.add(new Company("百度", "E:/img/1.jpg", "百度一下你就知道"));
        list.add(new Company("腾讯", "E:/img/3.jpg", "腾讯qq,交流的世界"));
        list.add(new Company("阿里巴巴", "E:/img/2.jpg", "阿里巴巴，马云的骄傲"));
        String fileName = map.get("fileName").toString();
        try {
            ExcelUtils.exportExcel(list, fileName, fileName, Company.class, fileName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入图片
     *
     * @param file
     * @return
     */
    @PostMapping("/imgimport")
    public CommonResult imgImport(@RequestParam("file") MultipartFile file) {
        List<Company> list = null;
        try {
            list = ExcelUtils.importExcel(file, Company.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResult(200, "导入成功", list);
    }

    /**
     * 使用模板excel导出数据
     *
     * @param response
     * @throws Exception
     */
    @PostMapping("/excelTemplate")
    public void makeExcelTemplate(@RequestBody Map<String, Object> param, HttpServletResponse response) {
        TemplateExportParams templatePath = new TemplateExportParams("E:/excel/用户信息文件模板.xls");
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("date", sdf.format(new Date()));
        map.put("user", "admin");
        IPage<User> ipages = userService.getList("", 1, 10);
        map.put("userList", ipages.getRecords());
        try {
            ExcelUtils.exportExcel(templatePath, map, param.get("fileName").toString(), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * EXCEL转html预览
     */
    @GetMapping("previewExcel")
    public void excelToHtml(HttpServletResponse response) throws Exception {
        ExcelUtils.excelToHtml("E:/excel/用户信息导入模板.xlsx", response);
    }

    /**
     * 使用模板word导出数据
     *
     * @param param
     * @param response
     */
    @PostMapping("/wordTemplate")
    public void makeWordTemplate(@RequestBody Map<String, Object> param, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("nativePlace", "湖北武汉");
        map.put("age", 20);
        map.put("nation", "汉族");
        map.put("phone", "15685654524");
        map.put("experience", "湖北武汉，工作三年，java工程师");
        map.put("evaluate", "优秀，善良，老实");
        map.put("picture", "");
        //设置图片,如果无图片，不设置即可
//        WordImageEntity image = new WordImageEntity();
//        image.setHeight(200);
//        image.setWidth(150);
//        image.setUrl("E:/excel/pic.jpg");
//        image.setType(WordImageEntity.URL);
//        map.put("picture", image);
        try {
            ExcelUtils.WordTemplateExport(map, "E:/excel/个人简历模板.docx", param.get("fileName").toString(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * word模板导出多页
     * @param param
     * @param response
     */
    @PostMapping("/wordTemplateMorePage")
    public void makeWordTemplateMorePage(@RequestBody Map<String, Object> param, HttpServletResponse response) {
        List<Map<String, Object>> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> person = new HashMap<>();
            person.put("name", "张三"+i);
            person.put("nativePlace", "湖北武汉"+i);
            person.put("age", 20+i);
            person.put("nation", "汉族");
            person.put("phone", "15685654524");
            person.put("experience", "湖北武汉，工作三年，java工程师");
            person.put("evaluate", "优秀，善良，老实");
            person.put("picture", "");
            list.add(person);
        }
        try {
            ExcelUtils.WordTemplateExportMorePage(list, "E:/excel/个人简历模板.docx", param.get("fileName").toString(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
