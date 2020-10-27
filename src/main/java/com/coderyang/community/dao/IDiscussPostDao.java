package com.coderyang.community.dao;

import com.coderyang.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by Chen
 * @date 2020/10/24.
 * @link
 * @describe
 */
@Mapper
public interface IDiscussPostDao {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名
    // 当使用动态sql的时候,只有一个参数,且要在if里使用,则必须使用别名
    int selectDiscussPostRows(@Param("userId") int userId);
}
