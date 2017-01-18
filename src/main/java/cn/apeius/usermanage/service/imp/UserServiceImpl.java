package cn.apeius.usermanage.service.imp;


import cn.apeius.usermanage.domain.EasyUIPage;
import cn.apeius.usermanage.domain.User;
import cn.apeius.usermanage.mapper.RoleMapper;
import cn.apeius.usermanage.mapper.UserMapper;
import cn.apeius.usermanage.service.UserService;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Asus on 2016/10/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    /**
     * 用户操作方法
     * @param pageNow
     * @param pageSize
     * @return
     */
    public List<User> queryAllUsers(Integer pageNow, Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        //紧接着的第一个查询会被执行分页
        List<User> list = userMapper.select(null);
        return list;
    }
    //查询用户
    public User queryUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    //增加用户
    public Integer addUser(User user) {
       return userMapper.insertSelective(user);
    }
    //删除用户
    public Integer deleteUserByIds(List<Object> ids) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        return userMapper.deleteByExample(example);
    }
    //注册
    @Override
    public void register(User user) {
        User record = new User();
        record.setUserName(user.getUserName());
        List<User> select = userMapper.select(record);
        if(select.size() != 0) throw new RuntimeException("用户名已存在");

        //数据库密码加密，盐值为用户名+bupt
        String p = new Md5Hash(user.getPassword(),user.getUserName() + "bupt").toHex();
        user.setPassword(p);
        int i = userMapper.insertSelective(user);
        if(0 == i) throw new RuntimeException("注册失败");

    }
    //登录
    @Override
    public User login(String username, String password) {
        User record = new User();
        record.setUserName(username);
        //数据库查询
        User user = userMapper.selectOne(record);
        if(user == null){
            throw new UnknownAccountException("用户名或密码错误");
        }
        //密码加密比较
        if(!user.getPassword().equals(new Md5Hash(password,username + "bupt").toHex()))
            throw new IncorrectCredentialsException("用户名或密码错误");

        return user;
    }

}
