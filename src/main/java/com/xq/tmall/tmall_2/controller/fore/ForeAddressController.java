package com.xq.tmall.tmall_2.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.tmall_2.controller.BaseController;
import com.xq.tmall.entity.Address;
import com.xq.tmall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ForeAddressController extends BaseController {
    @Autowired
    private AddressService addressService;
    @ResponseBody
    @RequestMapping(value = "address/{areaId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    protected String getAddressByAreaId(@PathVariable String areaId) {
        JSONObject o = new JSONObject();
        logger.info("获取AreaId为{}的地址信息");
        List<Address> addressList = addressService.getList(null, areaId);
        if (addressList == null || addressList.size() <= 0) {
            o.put("success", false);
            return o.toJSONString();
        }
        logger.info("获取该地址可能的子地址信息");
        List<Address> childAddressList = addressService.getList(null, addressList.get(0).getAddress_areaId());
        o.put("success", true);
        o.put("addressList", addressList);
        o.put("childAddressList", childAddressList);
        return o.toJSONString();
    }
    @RequestMapping("/")
    public  String ll(){
        return "fore/homePage";
    }
}