package com.example.easypoidemoadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.easypoidemoadmin.entity.User;

import java.util.List;

/**
 * @author zhongyushi
 * @date 2020/6/24 0024
 * @dec 描述
 */
public interface UserService {
    IPage<User> getList(String name, Integer page, Integer limit);

    int insertByBatch(List<User> list);
}
