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
}
