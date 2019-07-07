package com.mooc.house.userservice1.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.mooc.house.common.utils.BeanHelper;
import com.mooc.house.common.utils.HashUtils;
import com.mooc.house.userservice1.common.UserException;
import com.mooc.house.userservice1.mapper.UserMapper;
import com.mooc.house.userservice1.model.User;
import com.mooc.house.userservice1.utils.JwtHelper;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @Value("${file.prefix}")
    private String imgPrefix;

    /**
     * 1、通过缓存获取
     * 2、不存在时通过数据库获取
     * 3、将对象写入缓存
     * 4、返回对象
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        String key = "user:" + id;
        User user = null;
        String json = redisTemplate.opsForValue().get(key);
        if (Strings.isNullOrEmpty(json)) {
            userMapper.selectById(id);
            user.setAvatar(imgPrefix + user.getAvatar());
            String string = JSON.toJSONString(user);
            redisTemplate.opsForValue().set(key, string);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        } else {
            user = JSON.parseObject(json, User.class);
        }
        return user;
    }


    public List<User> getUserByQuery(User user) {
        List<User> users = userMapper.select(user);
        users.forEach(u -> {
            u.setAvatar(imgPrefix + u.getAvatar());
        });
        return users;
    }

    /**
     * 使用md5加混淆加密密码
     *
     * @param user
     * @param enableUrl
     */
    public void addAccount(User user, String enableUrl) {
        user.setPasswd(HashUtils.encryPassword(user.getPasswd()));
        BeanHelper.onInsert(user);
        userMapper.insert1(user);
        registerNotifyEmail(user.getEmail(), user.getEnableUrl());
    }

    private void registerNotifyEmail(String email, String enableUrl) {
        String randomKey = HashUtils.hashString(email) + RandomStringUtils.randomAlphabetic(10);
        redisTemplate.opsForValue().set(randomKey, email);
        redisTemplate.expire(randomKey, 1, TimeUnit.HOURS);
        String content = enableUrl + "?key=" + randomKey;
        mailService.sendMail("房屋平台激活邮件", content, email);
    }

    public void enable(String key) {
        String email = redisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(email))
            throw new UserException(UserException.Type.USER_WEAK_UP_FAIL, "无效的key");
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setEnable(1);
        userMapper.update(updateUser);
    }

    public User auth(String email, String passwd) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(passwd))
            throw new UserException(UserException.Type.USER_AUTH_FAIL, "user auth fail");
        User user = new User();
        user.setEmail(email);
        user.setPasswd(HashUtils.encryPassword(passwd));
        System.out.println("password:"+HashUtils.encryPassword(passwd));
        user.setEnable(1);
        List<User> list = getUserByQuery(user);
        if (!list.isEmpty()) {
            User retUser = list.get(0);
            onLogin(retUser);
            return retUser;
        }
        throw new UserException(UserException.Type.USER_AUTH_FAIL, "user auth fail");
    }

    private void onLogin(User retUser) {
        String token = JwtHelper.genToken(ImmutableMap.of("email", retUser.getEmail(), "name", retUser.getName(), "ts", Instant.now().getEpochSecond() + ""));
        renewToken(token, retUser.getEmail());
        retUser.setToken(token);
    }

    private String renewToken(String token, String email) {
        redisTemplate.opsForValue().set(email, token);
        redisTemplate.expire(email, 30, TimeUnit.MINUTES)
        ;
        return token;
    }

    public User getLoginedUserByToken(String token) {
        Map<String, String> map = null;
        try {
            map = JwtHelper.verifyToken(token);
        } catch (Exception e) {
            throw new UserException(UserException.Type.USER_NOT_LOGIN, "user not login");
        }
        String email = map.get("email");
        Long expired = redisTemplate.getExpire(email);
        if (expired > 0) {
            renewToken(token, email);
            User user = getUserByEmail(email);
            user.setToken(token);
            return user;
        }
        throw new UserException(UserException.Type.USER_NOT_LOGIN, "user not login");

    }

    private User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        List<User> list = userMapper.select(user);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new UserException(UserException.Type.USER_NOT_LOGIN, "user not login");
    }

    public void invalidate(String token) {
        Map<String,String> map =JwtHelper.verifyToken(token);
        redisTemplate.delete(map.get("email"));
    }
}
