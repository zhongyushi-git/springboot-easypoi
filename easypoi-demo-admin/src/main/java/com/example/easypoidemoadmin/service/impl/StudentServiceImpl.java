package com.example.easypoidemoadmin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.easypoidemoadmin.dao.StudentDao;
import com.example.easypoidemoadmin.entity.Student;
import com.example.easypoidemoadmin.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;


    @Override
    public IPage<Student> getList(String name, Integer page, Integer limit) {
        QueryWrapper wrapper = new QueryWrapper<Student>();
        wrapper.like("name", name);
        IPage<Student> pages = new Page<>(page, limit);
        IPage<Student> user = studentDao.selectList(pages, wrapper);
        return user;
    }

    @Override
    public void insertBatch(List<Student> list) {
        list.stream().forEach(item->{
            item.setBirth(DateUtil.parseDate(item.getBirthStr()));
            item.setAge(Integer.valueOf(item.getAgeStr()));
        });
        studentDao.insertBatch(list);
    }
}
