package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.LogOperacoesUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Log Operações", description = "Operações relacionadas a log de operações")
public class LogOperacoesController {

    @GetMapping("/{version}/relatorios/logs")
    @Operation(
            summary = "Listar todos os logs",
            description = "Lista todos os logs cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logs listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos logs."),
    })
    public Response getListaLogOperacoes(@PathVariable String version,
                                         @RequestParam(required = false) Integer codigoDeUsuario,
                                         @RequestParam(required = false) String tipoOperacao,
                                         @RequestParam(required = false) String dataInicio,
                                         @RequestParam(required = false) String dataFim,
                                         @RequestParam(required = false) Integer codigoOperacao) throws NullPointerException {
        return LogOperacoesUseCase.getListaLogOperacoes(MethodVersion.fromValue(version),
                codigoDeUsuario, tipoOperacao, dataInicio, dataFim, codigoOperacao);
    }

}
