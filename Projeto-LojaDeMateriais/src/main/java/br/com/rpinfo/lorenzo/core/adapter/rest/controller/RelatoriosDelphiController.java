package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.usecase.RelatoriosDelphiUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RelatoriosDelphiController {
    @GetMapping("/{version}/relatoriosdelphi/entradas")
    public Response getEntradasD(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getEntradasMovimentacoes(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/saidas")
    public Response getSaidasD(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getSaidasMovimentacoes(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/canceladas")
    public Response getCanceladas(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getMovimentacoesCanceladas(dataInicio, dataFim, MethodVersion.fromValue(version));
    }
}
