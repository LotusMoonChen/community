package com.coderyang.community.dao;

import com.coderyang.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by Chen
 * @date 2020/10/25.
 * @link
 * @describe
 */
@Mapper
public interface IUserDao {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}
