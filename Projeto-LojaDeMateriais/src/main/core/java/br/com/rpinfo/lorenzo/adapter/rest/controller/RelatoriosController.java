package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.RelatoriosUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelatoriosController {
    @GetMapping("/{version}/relatorios/entradas")
    public Response getEntradas(@PathVariable String version) throws ValidationException {
        return RelatoriosUseCase.getEntradas(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatorios/saidas")
    public Response getSaidas(@PathVariable String version) throws NullPointerException {
        return RelatoriosUseCase.getSaidas(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatorios/canceladas")
    public Response getCanceladas(@PathVariable String version) throws NullPointerException {
        return RelatoriosUseCase.getCanceladas(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatorios/comissoes")
    public Response getComissoes(@PathVariable String version) throws NullPointerException {
        return RelatoriosUseCase.getComissoes(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{transaction}/relatorios/entradasByTransaction")
    public Response getEntradasByTransaction(@PathVariable String version, @PathVariable String transaction) throws NullPointerException {
        return RelatoriosUseCase.getEntradaByTransaction(transaction, MethodVersion.fromValue(version));
    }
}
