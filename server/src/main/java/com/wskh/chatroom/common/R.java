package com.wskh.chatroom.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();
    private Object objectData;

    //让无参构造函数为私有，防止别人乱用
    private R(){}
    public static R ok(){
        //自己可以new自己，别人new不了
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    // return this:当前r这个对象，方便由于链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public static R fail(String msg) {
        return fail(400, msg, null);
    }

    public static R fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static R fail(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.message(msg);
        r.setObjectData(data);
        return r;
    }

    public static R succ(Object data) {
        return succ(ResultCode.SUCCESS, "操作成功", data);
    }

    public static R succ(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMessage(msg);
        r.setObjectData(data);
        return r;
    }
}
