package com.example.easypoidemoadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.easypoidemoadmin.entity.Student;

import java.util.List;

public interface StudentService {
    IPage<Student> getList(String name, Integer page, Integer limit);

    void insertBatch(List<Student> list);
}
