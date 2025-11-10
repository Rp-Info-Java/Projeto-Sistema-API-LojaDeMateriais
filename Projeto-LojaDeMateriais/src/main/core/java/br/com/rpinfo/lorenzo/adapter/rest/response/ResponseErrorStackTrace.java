package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseErrorStackTrace extends ResponseError {

    private String stackTrace;
    private Object content;

    public ResponseErrorStackTrace(Integer statusCode, String error, String stackTrace, Object content) {
        super(statusCode, error);
        this.stackTrace = stackTrace;
        this.content = content;
    }
}
