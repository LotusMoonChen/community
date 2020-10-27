package com.coderyang.community.controller;

import com.coderyang.community.entity.DiscussPost;
import com.coderyang.community.entity.Page;
import com.coderyang.community.entity.User;
import com.coderyang.community.service.DiscussPostService;
import com.coderyang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by Chen
 * @date 2020/10/25.
 * @link
 * @describe
 */
@Controller
public class HelloController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    private String getIndexPage(Model model, Page page){
        // 方法调用前，springmvc会自动实例化model和page，并将page注入到model中
        // 所以在thymeleaf中可以直接访问page对象中的数据
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.selectDiscussPosts(0, page.getOffSet(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null){
            for (DiscussPost discussPost : list) {
                Map<String, Object> map = new HashMap<>();
                User user = userService.findUserById(discussPost.getUserId());
                map.put("post", discussPost);
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "index";
    }
}
