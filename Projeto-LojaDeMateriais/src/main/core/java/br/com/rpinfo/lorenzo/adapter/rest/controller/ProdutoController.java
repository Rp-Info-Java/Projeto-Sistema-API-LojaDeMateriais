package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.ProdutosUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ProdutoController {

    @PostMapping("/{version}/produto/insert")
    public Response insertProduto(@PathVariable String version, @RequestBody ProdutosDto produto) throws ValidationException {
        return ProdutosUseCase.inserirProduto(produto, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/produto/getList")
    public Response getListProdutos(@PathVariable String version) throws NullPointerException {
        return ProdutosUseCase.getListaProdutos(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/produto/getProduto")
    public Response getProdutoById(@PathVariable String version, @PathVariable Integer id) throws NullPointerException {
        return ProdutosUseCase.getProdutoById(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/produto/update")
    public Response updateProduto(@PathVariable String version, @RequestBody ProdutosDto produto) throws ValidationException {
        return ProdutosUseCase.updateProduto(produto, MethodVersion.fromValue(version));
    }
}
