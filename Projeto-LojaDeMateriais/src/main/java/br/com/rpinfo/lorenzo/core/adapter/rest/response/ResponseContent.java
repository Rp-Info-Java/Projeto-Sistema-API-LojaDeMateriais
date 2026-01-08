package br.com.rpinfo.lorenzo.core.adapter.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import br.com.rpinfo.lorenzo.core.infrastructure.config.Configuration;

@Getter
public class ResponseContent extends Response {
    private final String appVersion = Configuration.version;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pages = null;
    private final Object content;

    public ResponseContent(Integer statusCode, Object content) {
        super(statusCode);
        this.content = content;
    }

    public ResponseContent(Integer statusCode, Object content, Integer pages) {
        super(statusCode);
        this.content = content;
        this.pages = pages;
    }
}
