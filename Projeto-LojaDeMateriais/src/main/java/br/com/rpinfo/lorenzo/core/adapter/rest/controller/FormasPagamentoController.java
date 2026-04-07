package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.usecase.FormasPagamentoUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Formaspagamento", description = "Operações relacionadas às formas de pagamento")
public class FormasPagamentoController {
    @GetMapping("/{version}/{codigo}/formaspagamento/getFpgto")
    public Response getFpgto(@PathVariable String version, @PathVariable Integer codigo) throws NullPointerException {
        return FormasPagamentoUseCase.getFpgtoById(codigo, MethodVersion.fromValue(version));
    }
}
