package br.com.rpinfo.lorenzo.core.adapter.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseError extends Response {

    private Object error;

    public ResponseError(Integer statusCode, Object error) {
        super(statusCode);
        this.error = error;
    }
}
