/**
 * FileName: ResponseResult
 * Date:     2022/6/9 17:03
 * Author: YuanXQ
 * Description:
 */
package com.woniu.knowledgepayment.commons.entity;

/**
 * <p>
 * 统一返回体格式
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/9 17:03
 * @since 1.0.0
 */
public class ResponseResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}