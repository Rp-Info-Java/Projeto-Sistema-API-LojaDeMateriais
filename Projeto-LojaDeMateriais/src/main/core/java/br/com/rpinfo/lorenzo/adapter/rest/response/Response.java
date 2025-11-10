package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response extends BaseDto {

    @JsonIgnore
    private Integer statusCode;

    public Response(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public static Response error(Integer statusCode, String content) {
        return new ResponseError(statusCode, content);
    }

    public static Response error(Throwable e) {
        if (e instanceof Exception ve) {
            return new ResponseError(400, ve.getMessage());
        }
        return new ResponseError(500, e.getMessage());
    }

    public static Response error(Integer statusCode, Throwable content) {
        return new ResponseError(statusCode, content);
    }

    public static Response ok(Integer statusCode, Object content) {
        return new ResponseContent(statusCode, content);
    }
}
