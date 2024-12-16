package com.nttdata.bankservice.entity.response;

import lombok.Data;

@Data
public class ResponseRedis {
    private Object data;
    private String source;

    public ResponseRedis(Object data, String source) {
        this.data = data;
        this.source = source;
    }

}
