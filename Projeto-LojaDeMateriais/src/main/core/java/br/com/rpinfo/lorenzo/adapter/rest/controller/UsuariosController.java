package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.UsuariosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.UsuariosUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuariosController {

    @PostMapping("/{version}/usuario/insert")
    @Operation(
            summary = "Cadastrar usuário",
            description = "Insere um novo usuário no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertUsuario(@PathVariable String version, @RequestBody UsuariosDto usuario) throws ValidationException {
        return UsuariosUseCase.inserirUsuario(usuario, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/usuario/getList")
    @Operation(
            summary = "Listar todos os usuários",
            description = "Lista todos os usuários cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos usuários."),
    })
    public Response getListUsuarios(@PathVariable String version) throws NullPointerException {
        return UsuariosUseCase.getListaUsuarios(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/usuario/getUsuario")
    @Operation(
            summary = "Buscar usuário pelo ID",
            description = "Busca um usuário no sistema pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhum usuário cadastrado."),
    })
    public Response getUsuario(@PathVariable Integer id, @PathVariable String version) throws NullPointerException {
        return UsuariosUseCase.getUsuario(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/usuario/update")
    @Operation(
            summary = "Atualiza dados do usuário",
            description = "Atualiza os dados de um usuário fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateUsuario(@PathVariable String version, @RequestBody UsuariosDto usuario) throws NullPointerException {
        return UsuariosUseCase.atualizarUsuario(usuario, MethodVersion.fromValue(version));
    }
}
