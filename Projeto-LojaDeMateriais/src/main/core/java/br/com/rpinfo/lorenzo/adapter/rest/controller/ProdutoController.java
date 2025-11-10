package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.ProdutosUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ProdutoController {

    @PostMapping("/{version}/produto/insert")
    public Response insertProduto(@PathVariable String version, @RequestBody ProdutosDto produto) throws SQLException {
        return ProdutosUseCase.inserirProduto(produto, MethodVersion.fromValue(version));
    }

//    @GetMapping("/{version}/produto/getList")
//    public Response getListProdutos(@PathVariable String version) throws SQLException {
//        return ProdutosUseCase.getListaProdutos(MethodVersion.fromValue(version));
//    }
}
