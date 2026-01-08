package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.dto.ConfiguracoesDto;
import br.com.rpinfo.lorenzo.core.application.usecase.ConfiguracoesUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Tag(name = "Configurações", description = "Operações relacionadas a configurações")
public class ConfiguracoesController {
    @PostMapping("/{version}/configuracoes/insert")
    @Operation(
            summary = "Cadastrar configuração",
            description = "Insere uma nova configuração no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Configuração cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertConfiguracao(@PathVariable String version, @RequestBody ConfiguracoesDto configDto) throws ValidationException {
        return ConfiguracoesUseCase.inserirConfiguracao(configDto, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/configuracoes/getList")
    @Operation(
            summary = "Listar todas as configurações",
            description = "Lista todos as configurações cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Configurações listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição das configurações."),
    })
    public Response getListaConfiguracoes(@PathVariable String version) throws NullPointerException {
        return ConfiguracoesUseCase.getListaConfiguracoes(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/configuracoes/getConfiguracao")
    @Operation(
            summary = "Buscar configuração pela ID",
            description = "Busca uma configuração no sistema pela ID fornecida"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Configuração encontrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhuma configuração cadastrada."),
    })
    public Response getConfiguracaoById(@PathVariable String version, @PathVariable Integer id) throws NullPointerException {
        return ConfiguracoesUseCase.getConfiguracao(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/configuracoes/update")
    @Operation(
            summary = "Atualizar configuração",
            description = "Atualiza os dados de uma configuração fornecida"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Configuração atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateConfiguracao(@PathVariable String version, @RequestBody ConfiguracoesDto config) throws NullPointerException {
        return ConfiguracoesUseCase.updateConfig(config, MethodVersion.fromValue(version));
    }
}
