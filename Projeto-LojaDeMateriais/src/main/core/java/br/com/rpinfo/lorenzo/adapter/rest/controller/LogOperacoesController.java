package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.LogOperacoesUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogOperacoesController {

    @GetMapping("/{version}/relatorios/logs")
    public Response getListaLogOperacoes(@PathVariable String version,
                                         @RequestParam(required = false) Integer codigoDeUsuario,
                                         @RequestParam(required = false) String tipoOperacao,
                                         @RequestParam(required = false) String dataInicio,
                                         @RequestParam(required = false) String dataFim) throws NullPointerException {
        return LogOperacoesUseCase.getListaLogOperacoes(MethodVersion.fromValue(version),
                codigoDeUsuario, tipoOperacao, dataInicio, dataFim);
    }

}
