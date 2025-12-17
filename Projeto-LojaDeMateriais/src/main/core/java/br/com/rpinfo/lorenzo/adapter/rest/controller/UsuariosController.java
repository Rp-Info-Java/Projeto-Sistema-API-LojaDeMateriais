package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.UsuariosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.UsuariosUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuariosController {

    @PostMapping("/{version}/usuario/insert")
    public Response insertUsuario(@PathVariable String version, @RequestBody UsuariosDto usuario) throws ValidationException {
        return UsuariosUseCase.inserirUsuario(usuario, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/usuario/getList")
    public Response getListUsuarios(@PathVariable String version) throws NullPointerException {
        return UsuariosUseCase.getListaUsuarios(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/usuario/getUsuario")
    public Response getUsuario(@PathVariable Integer id, @PathVariable String version) throws NullPointerException {
        return UsuariosUseCase.getUsuario(id, MethodVersion.fromValue(version));
    }

    @PutMapping("/{version}/usuario/update")
    public Response updateUsuario(@PathVariable String version, @RequestBody UsuariosDto usuario) throws NullPointerException {
        return UsuariosUseCase.atualizarUsuario(usuario, MethodVersion.fromValue(version));
    }
}
