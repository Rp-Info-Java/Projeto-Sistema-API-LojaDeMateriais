package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ClienteDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.ClienteUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ClienteController {

    @PostMapping("/{version}/cliente/insert")
    public Response insertCliente(@PathVariable String version, @RequestBody ClienteDto cliente) throws ValidationException {
        return ClienteUseCase.inserirCliente(cliente, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/cliente/getList")
    public Response getListClientes(@PathVariable String version) throws NullPointerException {
        return ClienteUseCase.getListaClientes(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/cliente/getCliente")
    public Response getCliente(@PathVariable Integer id, @PathVariable String version) throws NullPointerException {
        return ClienteUseCase.getCliente(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/cliente/update")
    public Response updateCliente(@PathVariable String version, @RequestBody ClienteDto cliente) throws NullPointerException {
        return ClienteUseCase.atualizaCliente(cliente, MethodVersion.fromValue(version));
    }
}
