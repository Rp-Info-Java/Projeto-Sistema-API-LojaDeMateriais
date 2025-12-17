package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.VendedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.VendedoresUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class VendedoresController {

    @PostMapping("/{version}/vendedor/insert")
    public Response insertVendedor(@PathVariable String version, @RequestBody VendedoresDto vendedor) throws ValidationException {
        return VendedoresUseCase.inserirVendedor(vendedor, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/vendedor/getList")
    public Response getListVendedores(@PathVariable String version) throws NullPointerException {
        return VendedoresUseCase.getListaVendedores(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/vendedor/getVendedor")
    public Response insertVendedor(@PathVariable String version, @PathVariable Integer id) throws NullPointerException {
        return VendedoresUseCase.getVendedorById(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/vendedor/update")
    public Response updateVendedor(@PathVariable String version, @RequestBody VendedoresDto vendedor) throws NullPointerException {
        return VendedoresUseCase.atualizaVendedor(vendedor, MethodVersion.fromValue(version));
    }

}
