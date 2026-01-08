package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.dto.ProdutosDto;
import br.com.rpinfo.lorenzo.core.application.usecase.ProdutosUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
public class ProdutoController {

    @PostMapping("/{version}/produto/insert")
    @Operation(
            summary = "Cadastrar produto",
            description = "Insere um novo produto no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertProduto(@PathVariable String version, @RequestBody ProdutosDto produto) throws ValidationException {
        return ProdutosUseCase.inserirProduto(produto, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/produto/getList")
    @Operation(
            summary = "Listar todos os produtos",
            description = "Lista todos os produtos cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos produtos."),
    })
    public Response getListProdutos(@PathVariable String version) throws NullPointerException {
        return ProdutosUseCase.getListaProdutos(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/produto/getProduto")
    @Operation(
            summary = "Buscar produto pelo ID",
            description = "Busca um produto no sistema pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhum produto cadastrado."),
    })
    public Response getProdutoById(@PathVariable String version, @PathVariable Integer id) throws NullPointerException {
        return ProdutosUseCase.getProdutoById(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/produto/update")
    @Operation(
            summary = "Atualiza dados do produto",
            description = "Atualiza os dados de um produto fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do produto atualizados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateProduto(@PathVariable String version, @RequestBody ProdutosDto produto) throws ValidationException {
        return ProdutosUseCase.updateProduto(produto, MethodVersion.fromValue(version));
    }
}
