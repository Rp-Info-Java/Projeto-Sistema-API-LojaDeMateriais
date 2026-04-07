package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.usecase.RelatoriosDelphiUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RelatoriosDelphiController {
    @GetMapping("/{version}/relatoriosdelphi/entradas")
    public Response getEntradasD(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getEntradasMovimentacoes(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/saidas")
    public Response getSaidasD(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getSaidasMovimentacoes(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/canceladas")
    public Response getCanceladas(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getMovimentacoesCanceladas(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/geralvendedores")
    public Response getGeralVendedores(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getMovimentacoesGeralVendedores(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/relpendfin")
    public Response getRelPendFin(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @RequestParam(required = false) String status, @RequestParam(required = false) String pagarReceber, @RequestParam(required = false) String tipoEnt, @RequestParam(required = false) Integer codEnt, @RequestParam(required = false) String pendente, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getPendenciasFinanceiras(dataInicio, dataFim, status, pagarReceber, tipoEnt, codEnt, pendente, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/relprodvend")
    public Response getRelProdVend(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getProdutosVendidos(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/reltransacao")
    public Response getRelTransacao(@RequestParam(required = false) String transacao, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getMovTransacao(transacao, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/relunitvendedor")
    public Response getRelUniVendedor(@RequestParam(required = false) Integer codVendedor, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getRelUnitVend(codVendedor, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/relprodutoscomprados")
    public Response getRelProdComp(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getRelProdComprados(dataInicio, dataFim, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/relatoriosdelphi/reldocbx")
    public Response getRelDocBx(@RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim, @PathVariable String version) throws ValidationException {
        return RelatoriosDelphiUseCase.getRelDocumentosBx(dataInicio, dataFim, MethodVersion.fromValue(version));
    }
}
