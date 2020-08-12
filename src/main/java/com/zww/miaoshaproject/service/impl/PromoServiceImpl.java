package com.zww.miaoshaproject.service.impl;

import com.zww.miaoshaproject.dao.PromoDOMapper;
import com.zww.miaoshaproject.dataobject.PromoDO;
import com.zww.miaoshaproject.service.PromoService;
import com.zww.miaoshaproject.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zww
 * @date 2020/8/12 15:08
 */
@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    private PromoDOMapper promoDOMapper;


    @Override
    public PromoModel getPromoByItemId(Integer itemId) {

        //获取对应商品的秒杀活动信息
        PromoDO promoDo = promoDOMapper.selectByItemId(itemId);

        //将Do转换成Model
        PromoModel promoModel = convertFromPromoDo(promoDo);

        //如果promoModel为null，没有该商品的秒杀活动
        if(promoModel==null){
            return null;
        }

        //判断当前时间和秒杀开始时间的关系
        //开始时间在当前时间之后
        if(promoModel.getStartDate().isAfterNow()){
            //秒杀还未开始
            promoModel.setStatus(1);
        }else if(promoModel.getEndDate().isBeforeNow()){
            //秒杀已经结束
            promoModel.setStatus(3);
        }else{
            //秒杀正在进行
            promoModel.setStatus(2);
        }



        return promoModel;
    }

    private PromoModel convertFromPromoDo(PromoDO promoDO){

        if(promoDO ==null){
            return null;
        }

        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        //单独这是价格的
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        //单独设置时间，mysql是sql.date,model是joda-date
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        return promoModel;

    }
}
