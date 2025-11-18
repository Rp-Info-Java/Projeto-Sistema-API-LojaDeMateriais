package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.MovProdutoUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovProdutosController {
    @PostMapping("/{version}/movimentacoes/insertEntrada")
    public Response insertEntradas(@PathVariable String version, @RequestBody MovProdutosCabDto mvpcDto) throws Exception {
        return MovProdutoUseCase.insertEntradas(mvpcDto, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/movimentacoes/insertSaidas")
    public Response insertSaidas(@PathVariable String version, @RequestBody MovProdutosCabDto mvpcDto) throws Exception {
        return MovProdutoUseCase.insertSaidas(mvpcDto, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/movimentacoes/getListMovimentacoesC")
    public Response getListMovimentacoesC(@PathVariable String version) throws Exception {
        return MovProdutoUseCase.getListMovimentacoesC(MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/{transaction}/movimentacoes/cancelarMovimentacao")
    public Response cancelarMovimentacao(@PathVariable String version, @PathVariable String transaction) throws Exception {
        return MovProdutoUseCase.cancelarMovimentacao(transaction, MethodVersion.fromValue(version));
    }
}
