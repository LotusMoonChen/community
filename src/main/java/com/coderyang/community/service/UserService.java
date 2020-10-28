package com.coderyang.community.service;

import com.coderyang.community.dao.IUserDao;
import com.coderyang.community.entity.User;
import com.coderyang.community.utils.CommunityConstant;
import com.coderyang.community.utils.CommunityUtils;
import com.coderyang.community.utils.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author by Chen
 * @date 2020/10/25.
 * @link
 * @describe
 */
@Service
public class UserService implements CommunityConstant {
    @Autowired
    private IUserDao dao;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id){
        return dao.selectById(id);
    }

    /**
     * 注册服务
     * @param user
     * @return
     */
    public Map<String, Object> register(User user){
        Map<String, Object> map = new HashMap<>();

        // 空值处理
        if (user == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(user.getUsername())){
            map.put("usernameMessage", "账号不能为空！");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())){
            map.put("passwordMessage", "密码不能为空！");
            return map;
        }

        if (StringUtils.isBlank(user.getEmail())){
            map.put("emailMessage", "邮箱不能为空！");
            return map;
        }


        // 验证账号
        User u = dao.selectByName(user.getUsername());
        if (u != null){
            map.put("usernameMessage", "该账号已存在");
            if (dao.selectByEmail(user.getEmail()) != null) {
                map.put("emailMessage", "该邮箱已被注册");
            }
            return map;
        }

        // 验证邮箱
        u = dao.selectByEmail(user.getEmail());
        if (u != null){
            map.put("emailMessage", "该邮箱已被注册");
            return map;
        }

        // 注册用户
        user.setSalt(CommunityUtils.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtils.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtils.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        dao.insertUser(user);

        // 给用户发送激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/activationCode
        String url = domain + contextPath + "/activation" + "/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    /**
     * 激活服务
     */
    @RequestMapping
    public int activation(int userId, String activationCode){
        User user = dao.selectById(userId);
        if (user.getStatus() == 1){
            return ACTIVATION_REPEAT;
        }else if (user.getActivationCode().equals(activationCode)){
            dao.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }

}
