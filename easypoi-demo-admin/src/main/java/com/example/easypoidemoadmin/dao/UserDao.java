package com.example.easypoidemoadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.easypoidemoadmin.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhongyushi
 * @date 2020/6/24 0024
 * @dec 描述
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    int insertByBatch(List<User> list);
}
