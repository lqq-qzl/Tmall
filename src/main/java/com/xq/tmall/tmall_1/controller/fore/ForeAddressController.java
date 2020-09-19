package com.xq.tmall.tmall_1.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xq.tmall.tmall_1.controller.BaseController;
import com.xq.tmall.tmall_1.entity.Address;
import com.xq.tmall.tmall_1.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ForeAddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    //根据address_areaId获取地址信息
    @ResponseBody
    @RequestMapping("address/{areaId}")
    public String getAddressByAreaId(@PathVariable String areaId, ModelMap map){
        List<Address> addressList = addressService.getList(null,areaId);
        if (addressList == null || addressList.size() <= 0){
            map.put("success",false);
            return new Gson().toJson(map);
        }
        List<Address> childAddressList = addressService.getList(null, addressList.get(0).getAddress_areaId());
        map.put("success",true);
        map.put("addressList",addressList);
        map.put("childAddressList",childAddressList);
        return new Gson().toJson(map);
    }

}
