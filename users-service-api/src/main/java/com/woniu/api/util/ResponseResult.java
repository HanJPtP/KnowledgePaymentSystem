package com.woniu.api.util;

public class ResponseResult<T> {
    private int code; // 状态码 200,成功，500:失败，403：无权
    private String msg; // 消息
    private T data; // 数据

    public ResponseResult() {
    }

    public ResponseResult(int code, T data) {
        this(code, "OK");
        this.data = data;
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static final ResponseResult<Void> SUCCESS = new ResponseResult<Void>(200, "OK");
    public static final ResponseResult<Void> NOTLOGINED = new ResponseResult<Void>(401, "未登录");
    public static final ResponseResult<Void> FORBIDDEN = new ResponseResult<Void>(403, "无权限");
    public static final ResponseResult<Void> Unauthenticated = new ResponseResult<Void>(402, "认证失败");
    public static final ResponseResult<Void> FAIL = new ResponseResult<Void>(500, "操作失败");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
