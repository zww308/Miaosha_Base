package com.zww.miaoshaproject.service;

import com.zww.miaoshaproject.error.BusinessException;
import com.zww.miaoshaproject.service.model.OrderModel;


/**
 * @author zww
 * @date 2020/8/12 9:31
 */
public interface OrderService {
    //1.通过前端url上传过来秒杀活动id，然后下担接口呢校验对应商品且活动已开始
    //2.直接在下单接口内判断对应商品是否在秒杀活动，若存在进行中的则以秒杀价格下单
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId,Integer amount) throws BusinessException;
}


