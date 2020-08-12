package com.zww.miaoshaproject.controller;

import com.zww.miaoshaproject.error.BusinessException;
import com.zww.miaoshaproject.error.EmBusinessError;
import com.zww.miaoshaproject.response.CommonReturnType;
import com.zww.miaoshaproject.service.OrderService;
import com.zww.miaoshaproject.service.model.OrderModel;
import com.zww.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zww
 * @date 2020/8/12 10:45
 */
@Controller("/order")
@RequestMapping("/order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController extends BaseController {


    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OrderService orderService;
    //封装下单请求
    @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name="itemId")Integer itemId,
                                        @RequestParam(name="amount")Integer amount,
                                        @RequestParam(name="promoId",required = false)Integer promoId)
            throws BusinessException {


        Boolean isLogin = (Boolean) this.httpServletRequest.getSession().getAttribute("IS_LOGIN");
//        System.out.println(isLogin.booleanValue());
        if(isLogin == null || !isLogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户还未登陆，不能下单");
        }
//        Boolean isLogin = (Boolean) this.httpServletRequest.getSession().getAttribute("IS_LOGIN");
//        if (isLogin == null || !isLogin.booleanValue()) {
//            throw new BussinessException(EmBusinessError.USER_NOT_LOGIN, "用户还未登录，不能下单");
//        }
        //获取登录信息（Boolean)
        UserModel userModel = (UserModel) this.httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel = orderService.createOrder(userModel.getId(),itemId,promoId,amount);

        return CommonReturnType.create(null);

    }

}
