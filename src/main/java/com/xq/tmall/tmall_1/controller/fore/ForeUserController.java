
package com.xq.tmall.tmall_1.controller.fore;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ForeUserController extends BaseController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;


    @RequestMapping("userDetails")
    public String goToUserDetail(HttpSession session, Map<String,Object> map){
        //"检查用户是否登录"
        Object userId = checkUser(session);
        if (userId != null) {
            //"获取用户信息"
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);

            //"获取用户所在地区级地址"
            String districtAddressId = user.getUser_address().getAddress_areaId();
            Address districtAddress = addressService.get(districtAddressId);
            //"获取市级地址信息"
            Address cityAddress = addressService.get(districtAddress.getAddress_regionId().getAddress_areaId());
            //"获取其他地址信息"
            List<Address> addressList = addressService.getRoot();
            List<Address> cityList = addressService.getList(null,cityAddress.getAddress_regionId().getAddress_areaId());
            List<Address> districtList = addressService.getList(null,cityAddress.getAddress_areaId());

            map.put("addressList", addressList);
            map.put("cityList", cityList);
            map.put("districtList", districtList);
            map.put("addressId", cityAddress.getAddress_regionId().getAddress_areaId());
            map.put("cityAddressId", cityAddress.getAddress_areaId());
            map.put("districtAddressId", districtAddressId);
            return  "fore/userDetails";
        } else {
            return "redirect:/login";
        }
    }
    //前台天猫-用户更换头像
    @ResponseBody
    @RequestMapping(value = "user/uploadUserHeadImage", produces = "application/json;charset=utf-8")
    public  String uploadUserHeadImage(@RequestParam MultipartFile file, HttpSession session, ModelMap map){
        //原始文件名
        String originalFileName = file.getOriginalFilename();
        //"获取图片原始文件名"
        String fileName = UUID.randomUUID().toString().replace("-","") + originalFileName;
        //"获取图片文件名"
        String filePath = session.getServletContext().getRealPath("/") + "res/images/item/userProfilePicture/" + fileName;

        //"文件上传路径"
        try {
            //"文件上传中..."
            file.transferTo(new File(filePath));
            //"文件上传成功！"
            map.put("success", true);
            map.put("fileName", fileName);
        } catch (IOException e) {
            //"文件上传失败！"
            e.printStackTrace();
            map.put("success", false);
        }
        return new Gson().toJson(map);
    }

    //前台天猫-用户详情更新
    @RequestMapping("user/update")
    public String userUpdate(HttpSession session, Map<String,Object> map,
                             @RequestParam(value = "user_nickname") String user_nickname  /*用户昵称 */,
                             @RequestParam(value = "user_realname") String user_realname  /*真实姓名*/,
                             @RequestParam(value = "user_gender") String user_gender  /*用户性别*/,
                             @RequestParam(value = "user_birthday") String user_birthday /*用户生日*/,
                             @RequestParam(value = "user_address") String user_address  /*用户所在地 */,
                             @RequestParam(value = "user_profile_picture_src", required = false) String user_profile_picture_src /* 用户头像*/,
                             @RequestParam(value = "user_password") String user_password/* 用户密码 */
    ) throws ParseException, UnsupportedEncodingException {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        } else {
            return "redirect:/login";
        }
        logger.info("创建用户对象");
        if (user_profile_picture_src != null && "".equals(user_profile_picture_src)) {
            user_profile_picture_src = null;
        }
        User userUpdate = new User()
                .setUser_id(Integer.parseInt(userId.toString()))
                .setUser_nickname(user_nickname)
                .setUser_realname(user_realname)
                .setUser_gender(Byte.valueOf(user_gender))
                .setUser_birthday(new SimpleDateFormat("yyyy-MM-dd").parse(user_birthday))
                .setUser_address(new Address().setAddress_areaId(user_address))
                .setUser_profile_picture_src(user_profile_picture_src)
                .setUser_password(user_password);
        logger.info("执行修改");
        if (userService.update(userUpdate)){
             logger.info("修改成功!跳转到用户详情页面");
             return "redirect:/userDetails";
         }
         throw new RuntimeException();
    }
}
