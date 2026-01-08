package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.usecase.RelatoriosUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Relatórios", description = "Operações relacionadas a relatórios")
public class RelatoriosController {
    @GetMapping("/{version}/relatorios/entradas")
    @Operation(
            summary = "Listar todas as movimentações de entradas",
            description = "Lista todas as movimentações de entradas cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentações de entrada listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição das movimentações."),
    })
    public Response getEntradas(@PathVariable String version) throws ValidationException {
        return RelatoriosUseCase.getEntradas(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatorios/saidas")
    @Operation(
            summary = "Listar todas as movimentações de saídas",
            description = "Lista todas as movimentações de saídas cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentações de saída listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição das movimentações."),
    })
    public Response getSaidas(@PathVariable String version) throws NullPointerException {
        return RelatoriosUseCase.getSaidas(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatorios/canceladas")
    @Operation(
            summary = "Listar todas as movimentações canceladas",
            description = "Lista todas as movimentações canceladas cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentações canceladas listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição das movimentações."),
    })
    public Response getCanceladas(@PathVariable String version) throws NullPointerException {
        return RelatoriosUseCase.getCanceladas(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatorios/comissoes")
    @Operation(
            summary = "Listar todas as comissões",
            description = "Lista todas as comissões de todos os vendedores"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comissões listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição das comissões."),
    })
    public Response getComissoes(@PathVariable String version) throws NullPointerException {
        return RelatoriosUseCase.getComissoes(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{transaction}/relatorios/entradasByTransaction")
    @Operation(
            summary = "Listar todas as movimentações de entrada por transação",
            description = "Lista todas as movimentações de entrada da transação fornecida"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentação de entrada mostrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição da movimentação."),
    })
    public Response getEntradasByTransaction(@PathVariable String version, @PathVariable String transaction) throws NullPointerException {
        return RelatoriosUseCase.getEntradaByTransaction(transaction, MethodVersion.fromValue(version));
    }
}
