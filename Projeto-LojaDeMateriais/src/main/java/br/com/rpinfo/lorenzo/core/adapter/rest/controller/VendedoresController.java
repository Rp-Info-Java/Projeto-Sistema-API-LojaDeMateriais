package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.dto.VendedoresDto;
import br.com.rpinfo.lorenzo.core.application.usecase.VendedoresUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Tag(name = "Vendedores", description = "Operações relacionadas a vendedores")
public class VendedoresController {

    @PostMapping("/{version}/vendedor/insert")
    @Operation(
            summary = "Cadastrar vendedor",
            description = "Insere um novo vendedor no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vendedor cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertVendedor(@PathVariable String version, @RequestBody VendedoresDto vendedor) throws ValidationException {
        return VendedoresUseCase.inserirVendedor(vendedor, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/vendedor/getList")
    @Operation(
            summary = "Listar todos os vendedores",
            description = "Lista todos os vendedores cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vendedores listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos vendedores."),
    })
    public Response getListVendedores(@PathVariable String version) throws NullPointerException {
        return VendedoresUseCase.getListaVendedores(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/vendedor/getVendedor")
    @Operation(
            summary = "Buscar vendedor pelo ID",
            description = "Busca um vendedor no sistema pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vendedor encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhum vendedor cadastrado."),
    })
    public Response getVendedor(@PathVariable String version, @PathVariable Integer id) throws NullPointerException {
        return VendedoresUseCase.getVendedorById(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/vendedor/update")
    @Operation(
            summary = "Atualiza dados do vendedor",
            description = "Atualiza os dados de um vendedor fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do vendedor atualizados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateVendedor(@PathVariable String version, @RequestBody VendedoresDto vendedor) throws NullPointerException {
        return VendedoresUseCase.atualizaVendedor(vendedor, MethodVersion.fromValue(version));
    }

}
