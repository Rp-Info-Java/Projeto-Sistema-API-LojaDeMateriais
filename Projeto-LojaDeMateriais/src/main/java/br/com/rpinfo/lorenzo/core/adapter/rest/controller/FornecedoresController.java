package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.rpinfo.lorenzo.core.application.dto.FornecedoresDto;
import br.com.rpinfo.lorenzo.core.application.usecase.FornecedoresUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Tag(name = "Fornecedores", description = "Operações relacionadas a fornecedores")
public class FornecedoresController {
    @PostMapping("/{version}/fornecedor/insert")
    @Operation(
            summary = "Cadastrar fornecedor",
            description = "Insere um novo fornecedor no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fornecedor cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertFornecedor(@PathVariable String version, @RequestBody FornecedoresDto fornecedor) throws ValidationException {
        return FornecedoresUseCase.inserirFornecedor(fornecedor, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/fornecedor/getList")
    @Operation(
            summary = "Listar todos os fornecedores",
            description = "Lista todos os fornecedores cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fornecedores listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos fornecedores."),
    })
    public Response getListFornecedores(@PathVariable String version) throws NullPointerException {
        return FornecedoresUseCase.getListaFornecedores(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/fornecedor/getFornecedor")
    @Operation(
            summary = "Buscar fornecedor pelo ID",
            description = "Busca um fornecedor no sistema pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fornecedor encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhum fornecedor cadastrado."),
    })
    public Response getFornecedor(@PathVariable Integer id, @PathVariable String version) throws NullPointerException {
        return FornecedoresUseCase.getFornecedor(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/fornecedor/update")
    @Operation(
            summary = "Atualiza dados do fornecedor",
            description = "Atualiza os dados de um fornecedor fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do fornecedor atualizados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateFornecedor(@RequestBody FornecedoresDto fornDto, @PathVariable String version) throws NullPointerException {
        return FornecedoresUseCase.updateFornecedor(fornDto, MethodVersion.fromValue(version));
    }
}
