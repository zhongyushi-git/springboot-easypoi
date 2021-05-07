package com.example.easypoidemoadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.easypoidemoadmin.dao.UserDao;
import com.example.easypoidemoadmin.entity.User;
import com.example.easypoidemoadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhongyushi
 * @date 2020/6/24 0024
 * @dec 描述
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public IPage<User> getList(String name, Integer page, Integer limit) {
        QueryWrapper wrapper=new QueryWrapper<User>();
        wrapper.like("name",name);
        IPage<User> pages=new Page<>(page,limit);
        IPage<User> user = userDao.selectPage(pages, wrapper);
        return user;
    }

    @Override
    public int insertByBatch(List<User> list) {
        return userDao.insertByBatch(list);
    }
}
