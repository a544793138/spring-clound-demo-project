package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  该类用于返回前端。 T 表示泛型，需要给前端的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    /**
     * 响应码，如 200，404...
     */
    private Integer code;

    /**
     * 详细信息
     */
    private String message;

    /**
     * 展示数据，如实体包装类
     */
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
