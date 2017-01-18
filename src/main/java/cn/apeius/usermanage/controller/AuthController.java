package cn.apeius.usermanage.controller;

import cn.apeius.usermanage.domain.User;
import cn.apeius.usermanage.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 2016/10/14.
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/logintest")
    public Map<String,Object> login(User user, HttpSession session){
        Map<String, Object> result = new HashMap<String, Object>();

        if("qm".equals(user.getName()) && "123".equals(user.getPassword())){
            //创建token
            String compactJws = Jwts.builder()
                    .setSubject("Joe")
                    .signWith(SignatureAlgorithm.HS512, (Key)session.getAttribute("key"))
                    .compact();

            result.put("status","200");
            result.put("msg","登录成功");
            result.put("token",compactJws);
        }else{
            result.put("status","400");
            result.put("msg","用户名或密码错误");
        }

        return result;
    }
}
