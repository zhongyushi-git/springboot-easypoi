package com.example.easypoidemoadmin.controller;

import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.easypoidemoadmin.entity.CommonResult;
import com.example.easypoidemoadmin.entity.Student;
import com.example.easypoidemoadmin.service.StudentService;
import com.example.easypoidemoadmin.utils.EasyPoiTool;
import com.example.easypoidemoadmin.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

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
        IPage<Student> iPage = studentService.getList(name, page, limit);
        return new CommonResult(200, "查询信息成功", iPage.getRecords(), iPage.getTotal());
    }

    /**
     * 导入学生信息
     *
     * @param file
     * @param response
     * @return
     */
    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try {
            ExcelImportResult<Student> importResult = ExcelUtils.importExcelMore(file, Student.class);
            //验证通过的数据
            List<Student> list = importResult.getList();
            //验证未通过的数据
            List<Student> failList = importResult.getFailList();
            studentService.insertBatch(list);
            if (failList != null && failList.size() > 0) {
                //修改导出的日期格式
                EasyPoiTool<Student> easyPoiUtil = new EasyPoiTool<>();
                easyPoiUtil.t = failList.get(0);
                //展示错误的列
                easyPoiUtil.changeAttribute("isColumnHidden", "errorMsg", false);
                //设置导出的格式
                easyPoiUtil.changeAttribute("exportFormat", "birthStr", "");
                //导出excel
                String title = "导入异常的数据";
                ExcelUtils.exportExcel(failList, title, title, Student.class, title, response);
                return null;
            }
            return new CommonResult(200, "信息导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(444, "信息导入失败");
        }

    }

    @PostMapping("/exportTemplate")
    public void exportTemplate(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
        List<Student> list = new ArrayList<>();
        ExcelUtils.exportExcel(list, (String) map.get("title"), (String) map.get("sheetName"), Student.class, (String) map.get("fileName"), response);
    }
}
