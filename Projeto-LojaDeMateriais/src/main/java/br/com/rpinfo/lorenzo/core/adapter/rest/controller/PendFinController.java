package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.dto.PagamentoDto;
import br.com.rpinfo.lorenzo.core.application.usecase.PendFinUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PendFin", description = "Operações relacionadas a pendências financeiras")
public class PendFinController {

    @PostMapping("/{version}/pendfin/insert")
    public Response insertPfin(@PathVariable String version, @RequestBody PagamentoDto pgtoDto) throws ValidationException{
        return PendFinUseCase.inserirPendFin(pgtoDto, MethodVersion.fromValue(version));
    }
}
