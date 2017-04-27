package com.chinagreentown.dmp.Constant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by yun on 2017/4/20.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result {

    private int status;

    private String message;

    private Object resp;

    private Result(int status, String message, Object resp) {
        this.status = status;
        this.message = message;
        this.resp = resp;
    }

    public static Result Success(Object resp) {
        return new Result(1, null, resp);
    }

    public static Result SuccessEmpty() {
        return new Result(0, null, null);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResp() {
        return resp;
    }

    public void setResp(Object resp) {
        this.resp = resp;
    }
}
