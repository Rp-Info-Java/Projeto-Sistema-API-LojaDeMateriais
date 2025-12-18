package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ClienteDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.ClienteUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClienteController {

    @PostMapping("/{version}/cliente/insert")
    @Operation(
            summary = "Cadastrar cliente",
            description = "Insere um novo cliente no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })

    public Response insertCliente(@PathVariable String version, @RequestBody ClienteDto cliente) throws ValidationException {
        return ClienteUseCase.inserirCliente(cliente, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/cliente/getList")
    @Operation(
            summary = "Listar todos os clientes",
            description = "Lista todos os clientes cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos clientes."),
    })
    public Response getListClientes(@PathVariable String version) throws NullPointerException {
        return ClienteUseCase.getListaClientes(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/cliente/getCliente")
    @Operation(
            summary = "Buscar cliente pelo ID",
            description = "Busca um cliente no sistema pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhum cliente cadastrado."),
    })
    public Response getCliente(@PathVariable Integer id, @PathVariable String version) throws NullPointerException {
        return ClienteUseCase.getCliente(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/cliente/update")
    @Operation(
            summary = "Atualiza dados do cliente",
            description = "Atualiza os dados de um cliente fornecido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do cliente atualizados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateCliente(@PathVariable String version, @RequestBody ClienteDto cliente) throws NullPointerException {
        return ClienteUseCase.atualizaCliente(cliente, MethodVersion.fromValue(version));
    }
}
