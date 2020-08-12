package com.zww.miaoshaproject.error;

/**
 * @author zww
 * @date 2020/8/10 9:14
 */

public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
