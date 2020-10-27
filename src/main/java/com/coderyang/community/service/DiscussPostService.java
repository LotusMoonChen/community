package com.coderyang.community.service;

import com.coderyang.community.dao.IDiscussPostDao;
import com.coderyang.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Chen
 * @date 2020/10/25.
 * @link
 * @describe
 */
@Service
public class DiscussPostService {
    @Autowired
    private IDiscussPostDao dao;

    public List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit){
        return dao.selectDiscussPosts(userId, offset, limit);
    }

    public int selectDiscussPostRows(int userId){
        return dao.selectDiscussPostRows(userId);
    }

}
