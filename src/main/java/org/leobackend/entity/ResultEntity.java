package org.leobackend.entity;

import lombok.Data;

/**
 * Created on 2021-01-02.
 * 返回结果封装
 *
 * @author Leo
 */
@Data
public class ResultEntity <T> {

    private Integer status;
    private String message;
    private T data;

    public ResultEntity () {
    }

    public ResultEntity (Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultEntity<T> success (T data) {
        return new ResultEntity<T>(ResultStatusEnum.OK.getValue(), ResultStatusEnum.OK.getMessage(), data);
    }

    public static <T> ResultEntity<T> success () {
        return new ResultEntity<T>(ResultStatusEnum.OK.getValue(), ResultStatusEnum.OK.getMessage(), null);
    }

    public static <T> ResultEntity<T> failed () {
        return new ResultEntity<T>(ResultStatusEnum.FAILED.getValue(), ResultStatusEnum.FAILED.getMessage(), null);
    }

}
