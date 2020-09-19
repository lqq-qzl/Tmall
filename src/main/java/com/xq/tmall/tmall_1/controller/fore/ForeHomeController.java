package com.xq.tmall.tmall_1.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xq.tmall.tmall_1.controller.BaseController;
import com.xq.tmall.tmall_1.entity.Category;
import com.xq.tmall.tmall_1.entity.Product;
import com.xq.tmall.tmall_1.entity.User;
import com.xq.tmall.tmall_1.service.CategoryService;
import com.xq.tmall.tmall_1.service.ProductImageService;
import com.xq.tmall.tmall_1.service.ProductService;
import com.xq.tmall.tmall_1.service.UserService;
import com.xq.tmall.tmall_1.util.OrderUtil;
import com.xq.tmall.tmall_1.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前台天猫-主页
 */
@Controller
public class ForeHomeController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;

    //转到前台天猫-主页
    @RequestMapping("/")
    public String goToPage(HttpSession session, Map<String, Object> map) {
        //检查用户是否登陆
        Object userId = checkUser(session);
        if (userId != null) {
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        //侧边栏内容
        List<Category> categoryList = categoryService.getList(null, null);
        //主页中间部分，左侧大图
        for (Category category : categoryList) {
            List<Product> productList = productService.getList(new Product().setProduct_category(category), new Byte[]{0, 2}, new OrderUtil("product_id", true), new PageUtil(0, 8));
            if (productList != null) {
                //右侧小图
                for (Product product : productList) {
                    Integer product_id = product.getProduct_id();
                    product.setSingleProductImageList(productImageService.getList(product_id, (byte) 0, new PageUtil(0, 1)));
                }
            }
            category.setProductList(productList);
        }
        map.put("categoryList", categoryList);
        //促销产品轮播
        List<Product> specialProductList = productService.getList(null, new Byte[]{2}, null, new PageUtil(0, 6));
        map.put("specialProductList", specialProductList);

        return "fore/homePage";
    }

    //转到前台天猫-错误页
    @RequestMapping("errors")
    public String goToErrorPage() {
        return "fore/errorPage";
    }

    //获取主页分类下产品信息,侧边栏里的隐藏内容，商品标题

    @ResponseBody
    @RequestMapping("product/nav/{category_id}")
    public String getProductByNav(@PathVariable("category_id") Integer category_id, ModelMap map) {
        if (category_id == null){
            map.put("success",false);
            return new Gson().toJson(map);
        }
        //Product product, PageUtil pageUtil,获取产品标题
//        logger.info("获取分类ID为{}的产品标题数据", category_id);
        List<Product> productList = productService.getTitle(new Product().setProduct_category(new Category().setCategory_id(category_id)), new PageUtil(0,40));
        System.out.println(productList.toString());
        List<List<Product>> complexProductList = new ArrayList<>(8);
        List<Product> products = new ArrayList<>(5);
        for (int i = 0; i < productList.size(); i++) {
            if (i % 5 == 0){
                complexProductList.add(products);
                products = new ArrayList<>(5);
            }
            products.add(productList.get(i));
        }
        complexProductList.add(products);
        Category category = new Category().setCategory_id(category_id).setComplexProductList(complexProductList);
        map.put("success", true);
        map.put("category", category);
        return new Gson().toJson(map);
    }
}
