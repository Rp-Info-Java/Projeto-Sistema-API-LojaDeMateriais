package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.FornecedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.FornecedoresUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class FornecedoresController {
    @PostMapping("/{version}/fornecedor/insert")
    public Response insertFornecedor(@PathVariable String version, @RequestBody FornecedoresDto fornecedor) throws SQLException {
        return FornecedoresUseCase.inserirFornecedor(fornecedor, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/fornecedor/getList")
    public Response getListFornecedores(@PathVariable String version) throws SQLException {
        return FornecedoresUseCase.getListaFornecedores(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/fornecedor/getFornecedor")
    public Response getFornecedor(@PathVariable Integer id, @PathVariable String version) throws SQLException {
        return FornecedoresUseCase.getFornecedor(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/fornecedor/update")
    public Response updateFornecedor(@RequestBody FornecedoresDto fornDto, @PathVariable String version) throws SQLException{
        return FornecedoresUseCase.updateFornecedor(fornDto, MethodVersion.fromValue(version));
    }
}
