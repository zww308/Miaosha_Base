package com.zww.miaoshaproject.service;

import com.zww.miaoshaproject.error.BusinessException;
import com.zww.miaoshaproject.service.model.UserModel;

/**
 * @author zww
 * @date 2020/8/9 14:28
 */

public interface UserService {
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}
