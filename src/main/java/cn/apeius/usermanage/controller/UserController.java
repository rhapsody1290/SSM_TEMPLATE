package cn.apeius.usermanage.controller;

import cn.apeius.usermanage.domain.EasyUIPage;
import cn.apeius.usermanage.domain.User;
import cn.apeius.usermanage.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 2016/10/7.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    //页面跳转合并
    @RequestMapping(value = "/page/{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }


    @ResponseBody
    @RequestMapping(value = "/main")
    public Map<String,Object> main(@RequestParam(value = "token",defaultValue = "") String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            /*Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            if(!Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject().equals("Joe")) throw new RuntimeException();
            //OK, we can trust this JWT
            result.put("status","200");*/
        }catch (Exception e){
            //don't trust the JWT!
            result.put("status","400");
        }

        return result;
    }


    /**
     * 用户列表
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public EasyUIPage list(@RequestParam(value = "page", defaultValue = "1") Integer pageNow,
                           @RequestParam(value = "rows", defaultValue = "5") Integer pageSize){


        List<User> users = userService.queryAllUsers(pageNow,pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(users);

        EasyUIPage easyUIPage = new EasyUIPage();
        easyUIPage.setTotal(pageInfo.getTotal());
        easyUIPage.setRows(pageInfo.getList());

        return easyUIPage;
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String, String> save(User user){
        Map<String,String> map = new HashMap<String, String>();
        try{
            Integer num = userService.addUser(user);
            if(num > 0){
                map.put("status","200");
            }else{
                map.put("status","500");
            }
        }catch (Exception e){
            map.put("status","500");
        }
        return map;
    }

    /**
     * 导出用户信息
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "export/excel")
    public ModelAndView export(@RequestParam(value = "page", defaultValue = "1") Integer pageNow,
                               @RequestParam(value = "rows", defaultValue = "5") Integer pageSize){
        //1、查询需要导出的数据
        List<User> users = this.userService.queryAllUsers(pageNow, pageSize);

        //2、传递数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",users);
        mv.setViewName("userExcel");//定义到自定义的excel视图
        return mv;
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    //springmvc会自动把逗号切割，变成一个数组
    public Map<String,String> deleteById(@RequestParam(value = "ids") List<Object> ids){
        Integer num = this.userService.deleteUserByIds(ids);
        Map<String,String> map = new HashMap<String, String>();
        if(num > 0){
            map.put("status","200");
        }else{
            map.put("status","208");
        }
        return map;
    }

}
