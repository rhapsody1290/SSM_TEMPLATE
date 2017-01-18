package cn.apeius.usermanage.controller;

/**
 * Created by Asus on 2016/10/17.
 */

import cn.apeius.usermanage.util.VerifyCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping(value="/login")
    public Map<String, String> login(@RequestParam(value = "username",defaultValue = "") String username,
                                     @RequestParam(value = "password",defaultValue = "") String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token.setRememberMe(true);

        //获取当前的Subject
        Subject subject = SecurityUtils.getSubject();
        String message = null;
        try {
            //调用login(token)方法时,会进入MyRealm.doGetAuthenticationInfo()方法中进行验证
            subject.login(token);
        }catch(UnknownAccountException e){
            message = e.getMessage();
            System.out.println("用户名不存在");
        }catch(IncorrectCredentialsException e){
            message = e.getMessage();
            System.out.println("密码错误");
        }

        //返回结果
        Map<String, String> result = new HashMap<String, String>();
        if(message != null){
            result.put("status","400");
            result.put("msg",message);
        }else{
            result.put("status","200");
            result.put("token",subject.getSession().getId().toString());
        }
        return result;
    }

    //测试服务端维护状态
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/loginmain")
    public void main(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println(subject.getPrincipal());
    }

    /**
     * 用户登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @RequestMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute("verifyCode", verifyCode);
        System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }
}