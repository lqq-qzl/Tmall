package com.xq.tmall.tmall_1.dao;

import com.xq.tmall.tmall_1.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressMapper {
    //新增地址信息
    Integer insertOne(@Param("address") Address address);
    //修改地址信息
    Integer updateOne(@Param("address") Address address);
    //根据名称和地址编号查找地质信息
    List<Address> select(@Param("address_name") String address_name, @Param("address_regionId") String address_regionId);
    //根据地址编号查找地址
    Address selectOne(@Param("address_areaId") String address_areaId);
    //根据地址编号查找属于当前地址的地区
    List<Address> selectRoot();
}