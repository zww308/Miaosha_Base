package com.zww.miaoshaproject.service;

import com.zww.miaoshaproject.service.model.PromoModel;

/**
 * @author zww
 * @date 2020/8/12 15:06
 */

public interface PromoService {

    PromoModel getPromoByItemId(Integer itemId);
}
