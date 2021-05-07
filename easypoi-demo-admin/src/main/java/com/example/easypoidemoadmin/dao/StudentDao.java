package com.example.easypoidemoadmin.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.easypoidemoadmin.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface StudentDao extends BaseMapper<Student> {

    IPage<Student> selectList(IPage<Student> page, @Param(Constants.WRAPPER) Wrapper<Student> queryWrapper);

    void insertBatch(List<Student> list);
}
