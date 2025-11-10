package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;

import java.util.List;

public class ResponseHandler {

    public static Response ok(Object content, MethodVersion version) {
        return createResponse(200, content, version);
    }

    private static Response createResponse(Integer statusCode, Object content, MethodVersion version) {
        if (version.ordinal() >= MethodVersion.V_1_0.ordinal() && version != MethodVersion.V_1_0) {
            return Response.ok(statusCode, content);
        } else {
            List<Integer> okCodes = List.of(102, 200, 201, 202, 204, 206);
            String statusMessage = okCodes.contains(statusCode) ? "ok" : "error";
            return Response.ok(200, content);
        }
    }

}
