package tian.web;

import com.alibaba.fastjson.JSON;
import tian.web.enums.ResCode;

import java.io.Serializable;

/**
 * 返回参数工具类
 * @author tian
 * @date 2020/9/11
 */
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;


    public Result(ResCode resCode, T data){
        this.code=resCode.getCode();
        this.message=resCode.getMessage();
        this.data=data;
    }

    /**
     * 自定义消息返回值
     * @param resCode
     * @param message
     * @param data
     */
    public Result(ResCode resCode,String message,T data){
        this.code=resCode.getCode();
        this.message=message;
        this.data=data;
    }

    public Result() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(ResCode resCode) {
        this.code = resCode.getCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 转成json 自动序列化
     * @return
     */
    public Object getData() {
        return JSON.toJSON(data);
    }

    public void setData(T data) {
        this.data = data;
    }
}
