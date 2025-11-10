package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.controller;

import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MunicipiosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.usecase.MunicipiosUseCase;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class MunicipiosController {

    @PostMapping("/{version}/municipio/insert")
    public Response insertMunicipio(@PathVariable String version, @RequestBody MunicipiosDto municipio) throws SQLException {
        return MunicipiosUseCase.inserirMunicipio(municipio, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/municipio/getList")
    public Response getListMunicipios(@PathVariable String version) throws ValidationException {
        return MunicipiosUseCase.getListaMunicipios(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/municipio/getMunicipio")
    public Response getMunicipio(@PathVariable Integer id, @PathVariable String version) throws ValidationException {
        return MunicipiosUseCase.getMunicipio(MethodVersion.fromValue(version), id);
    }

    @PutMapping("/{version}/municipio/update")
    public Response updateMunicipio(@PathVariable String version, @RequestBody MunicipiosDto municipio) throws ValidationException {
        return MunicipiosUseCase.updateMunicipio(MethodVersion.fromValue(version), municipio);
    }

    @GetMapping("/{version}/{uf}/municipio/getListByUf")
    public Response getListMunicipiosByUf(@PathVariable String version, @PathVariable String uf) throws ValidationException {
        return MunicipiosUseCase.getListaMunicipiosByUf(MethodVersion.fromValue(version), uf);
    }
}
