package com.xq.tmall.tmall_1.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.tmall_1.controller.BaseController;
import com.xq.tmall.tmall_1.entity.Address;
import com.xq.tmall.tmall_1.entity.User;
import com.xq.tmall.tmall_1.service.AddressService;
import com.xq.tmall.tmall_1.service.UserService;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xq.tmall.tmall_1.controller.BaseController;
import com.xq.tmall.tmall_1.entity.Address;
import com.xq.tmall.tmall_1.entity.User;
import com.xq.tmall.tmall_1.service.AddressService;
import com.xq.tmall.tmall_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class ForeRegisterController extends BaseController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    //转到前台天猫-用户注册页
    @RequestMapping("register")
    public String goToPage(Map<String,Object> map) {
        String addressId = "110000";
        String cityAddressId = "110100";
        //"获取省份信息"
        List<Address> addressList = addressService.getRoot();
        //"获取addressId为{}的市级地址信息"
        List<Address> cityAddress = addressService.getList(null, addressId);
        //"获取cityAddressId为{}的区级地址信息"
        List<Address> districtAddress = addressService.getList(null, cityAddressId);
        map.put("addressList", addressList);
        map.put("cityList", cityAddress);
        map.put("districtList", districtAddress);
        map.put("addressId", addressId);
        map.put("cityAddressId", cityAddressId);
        //"转到前台-用户注册页"
        return "fore/register";
    }

    //天猫前台-用户注册
    @ResponseBody
    @RequestMapping(value = "register/doRegister",produces = "application/json;charset=UTF-8")
    public String register(
            @RequestParam(value = "user_name") String user_name  /*用户名 */,
            @RequestParam(value = "user_nickname") String user_nickname  /*用户昵称 */,
            @RequestParam(value = "user_password") String user_password  /*用户密码*/,
            @RequestParam(value = "user_gender") String user_gender  /*用户性别*/,
            @RequestParam(value = "user_birthday") String user_birthday /*用户生日*/,
            @RequestParam(value = "user_address") String user_address  /*用户所在地 */,
            ModelMap map
    ) throws ParseException {
        //"验证用户名是否存在"
        Integer count = userService.getTotal(new User().setUser_name(user_name));
        if (count > 0) {
            //"用户名已存在，返回错误信息!"
            map.put("success", false);
            map.put("msg", "用户名已存在，请重新输入！");
            return new Gson().toJson(map);
        }
        //"创建用户对象"
        User user = new User()
                .setUser_name(user_name)
                .setUser_nickname(user_nickname)
                .setUser_password(user_password)
                .setUser_gender(Byte.valueOf(user_gender))
                .setUser_birthday(new SimpleDateFormat("yyyy-MM-dd").parse(user_birthday))
                .setUser_address(new Address().setAddress_areaId(user_address))
                .setUser_homeplace(new Address().setAddress_areaId("130000"));
        //"用户注册"
        if (userService.add(user)) {
            //"注册成功"
            map.put("success", true);
            return new Gson().toJson(map);
        } else {
            throw new RuntimeException();
        }
    }
}
