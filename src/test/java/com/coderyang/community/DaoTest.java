package com.coderyang.community;

import com.coderyang.community.dao.IDiscussPostDao;
import com.coderyang.community.dao.IUserDao;
import com.coderyang.community.entity.DiscussPost;
import com.coderyang.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author by Chen
 * @date 2020/10/24.
 * @link
 * @describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class DaoTest {

    @Autowired
    IDiscussPostDao discussPostDao;
    @Autowired
    IUserDao userDao;

    @Test
    public void testSelectDiscussPosts() {
        // 测试查找全部 ---- 测试通过
        List<DiscussPost> posts = discussPostDao.selectDiscussPosts(0, 0, 10);
        for (DiscussPost post : posts) {
            System.out.println(post);
        }

        // 测试根据user_id查找 ----- 测试通过
        posts = discussPostDao.selectDiscussPosts(149, 0, 10);
        for (DiscussPost post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void testSelectDiscussPostRows() {
        // 测试查找数据量 ----- 测试通过
        int counts = discussPostDao.selectDiscussPostRows(0);
        System.out.println(counts);
    }

    @Test
    public void testFindById() {
        User user = userDao.selectById(149);
        System.out.println(user);

        user = userDao.selectByName("lhh");
        System.out.println(user);

        user = userDao.selectByEmail("nowcoder138@sina.com");
        System.out.println(user);

        /*User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("123456");
        user1.setSalt("12a41");
        user1.setEmail("test@qq.com");
        user1.setHeaderUrl("http://images.nowcoder.com/head/101.png");
        user1.setCreateTime(new Date());
        int i = userDao.insertUser(user1);
        System.out.println(i);*/

        /*int i = userDao.updateHeader(151, "http://images.nowcoder.com/head/105.png");
        System.out.println(i);*/

        /*int i = userDao.updatePassword(151, "mine");
        System.out.println(i);*/

        int i = userDao.updateStatus(151, 1);
        System.out.println(i);
    }
}
