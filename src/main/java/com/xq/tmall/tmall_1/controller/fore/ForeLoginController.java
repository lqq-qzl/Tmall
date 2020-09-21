package com.xq.tmall.tmall_1.controller.fore;

import com.google.gson.Gson;
import com.xq.tmall.tmall_1.controller.BaseController;
import com.xq.tmall.tmall_1.entity.User;
import com.xq.tmall.tmall_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 前台天猫-登陆页
 */
@Controller
public class ForeLoginController extends BaseController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //跳转登录页
    @RequestMapping("login")
    public String goLogin(){
        return "fore/loginPage";
    }

    //登陆验证
    @ResponseBody
    @RequestMapping("login/doLogin")
    public String vali_logon(HttpSession session, @RequestParam String username, @RequestParam String password, ModelMap map){
        User user = userService.login(username,password);
//        boolean flag;

        if (user != null){
            session.setAttribute("userId", user.getUser_id());
            map.put("success",true);
        } else {
            map.put("success",false);
        }

//        if (flag){
//            session.setAttribute("userId", user.getUser_id());
////            jsonObject.put("success", true);
//            flag = true;
//
//        }else {
////            jsonObject.put("success", false);
//            flag = false;
//        }
        return new Gson().toJson(map);
    }

    //退出当前账号
    @RequestMapping("login/logout")
    public String logout(HttpSession session){
        Object userId = session.getAttribute("userId");

        if (userId == null){
            return "redirect:/";
        } else {
            session.removeAttribute("userId");
            //将session设置为失效
            session.invalidate();
            return "redirect:/";
        }
    }

}
