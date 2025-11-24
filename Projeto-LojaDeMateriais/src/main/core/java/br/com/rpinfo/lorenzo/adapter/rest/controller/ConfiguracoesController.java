package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.ConfiguracoesUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ConfiguracoesController {
    @PostMapping("/{version}/configuracoes/insert")
    public Response insertConfiguracao(@PathVariable String version, @RequestBody ConfiguracoesDto configDto) throws SQLException {
        return ConfiguracoesUseCase.inserirConfiguracao(configDto, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/configuracoes/getList")
    public Response getListaConfiguracoes(@PathVariable String version) throws SQLException{
        return ConfiguracoesUseCase.getListaConfiguracoes(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/configuracoes/getConfiguracao")
    public Response getConfiguracaoById(@PathVariable String version, @PathVariable Integer id) throws SQLException{
        return ConfiguracoesUseCase.getConfiguracao(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/configuracoes/update")
    public Response updateConfiguracao(@PathVariable String version, @RequestBody ConfiguracoesDto config) throws SQLException{
        return ConfiguracoesUseCase.updateConfig(config, MethodVersion.fromValue(version));
    }
}
