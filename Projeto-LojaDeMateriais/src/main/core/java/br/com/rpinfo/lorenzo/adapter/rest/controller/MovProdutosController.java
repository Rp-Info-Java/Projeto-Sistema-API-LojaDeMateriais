package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.MovProdutoUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Movimentações", description = "Operações relacionadas a movimentações")
public class MovProdutosController {
    @PostMapping("/{version}/movimentacoes/insertEntrada")
    @Operation(
            summary = "Cadastrar movimentação de entrada",
            description = "Insere uma nova movimentação de entrada no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentação de entrada cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertEntradas(@PathVariable String version, @RequestBody MovProdutosCabDto mvpcDto) throws ValidationException {
        return MovProdutoUseCase.insertEntradas(mvpcDto, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/movimentacoes/insertSaidas")
    @Operation(
            summary = "Cadastrar movimentação de saída",
            description = "Insere uma nova movimentação de saída no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentação de saída cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertSaidas(@PathVariable String version, @RequestBody MovProdutosCabDto mvpcDto) throws NullPointerException {
        return MovProdutoUseCase.insertSaidas(mvpcDto, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/movimentacoes/getListMovimentacoesC")
    @Operation(
            summary = "Listar todas as movimentações cadastradas",
            description = "Lista todos os cabeçalhos e detalhamentos de movimentações cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentações listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição das movimentações."),
    })
    public Response getListMovimentacoesC(@PathVariable String version) throws NullPointerException {
        return MovProdutoUseCase.getListMovimentacoesC(MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/{transaction}/movimentacoes/cancelarMovimentacao")
    @Operation(
            summary = "Cancelar movimentação",
            description = "Cancela uma movimentação cadastrada"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentação cancelada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição da movimentação."),
    })
    public Response cancelarMovimentacao(@PathVariable String version, @PathVariable String transaction) throws NullPointerException {
        return MovProdutoUseCase.cancelarMovimentacao(transaction, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{transaction}/movimentacoes/getMovimentacaoByTransaction")
    @Operation(
            summary = "Buscar movimentação pela transação",
            description = "Busca uma movimentação no sistema pela transação fornecida"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimentação encontrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "A transação fornecida não corresponde a nenhuma movimentação cadastrada."),
    })
    public Response getMovimentacao(@PathVariable String version, @PathVariable String transaction) throws NullPointerException {
        return MovProdutoUseCase.getMovimentacaoByTransac(transaction, MethodVersion.fromValue(version));
    }
}
