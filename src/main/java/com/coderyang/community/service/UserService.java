package com.coderyang.community.service;

import com.coderyang.community.dao.IUserDao;
import com.coderyang.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Chen
 * @date 2020/10/25.
 * @link
 * @describe
 */
@Service
public class UserService {
    @Autowired
    private IUserDao dao;

    public User findUserById(int id){
        return dao.selectById(id);
    }

}
