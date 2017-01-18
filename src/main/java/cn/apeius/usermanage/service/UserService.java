package cn.apeius.usermanage.service;

import cn.apeius.usermanage.domain.EasyUIPage;
import cn.apeius.usermanage.domain.User;

import java.util.List;

/**
 * Created by Asus on 2016/10/7.
 */
public interface UserService {

    //查询所有用户
    public List<User> queryAllUsers(Integer pageNow, Integer pageSize);
    //查询用户
    public User queryUserById(Long userId);
    //增加用户
    public Integer addUser(User user);
    //删除用户
    public Integer deleteUserByIds(List<Object> ids);
    //注册,失败抛出异常
    public void register(User user);
    //登录
    public User login(String username, String password);


}
