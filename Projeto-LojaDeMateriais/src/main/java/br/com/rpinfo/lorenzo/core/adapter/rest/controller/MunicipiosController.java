package br.com.rpinfo.lorenzo.core.adapter.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.application.dto.MunicipiosDto;
import br.com.rpinfo.lorenzo.core.application.usecase.MunicipiosUseCase;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Tag(name = "Municipios", description = "Operações relacionadas a municipios")
public class MunicipiosController {

    @PostMapping("/{version}/municipio/insert")
    @Operation(
            summary = "Cadastrar municipio",
            description = "Insere um novo municipio no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipio cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    public Response insertMunicipio(@PathVariable String version, @RequestBody MunicipiosDto municipio) throws ValidationException {
        return MunicipiosUseCase.inserirMunicipio(municipio, MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/municipio/getList")
    @Operation(
            summary = "Listar todos os municipios",
            description = "Lista todos os municipios cadastrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipios listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos municipios."),
    })
    public Response getListMunicipios(@PathVariable String version) throws NullPointerException {
        return MunicipiosUseCase.getListaMunicipios(MethodVersion.fromValue(version));
    }

    @GetMapping("/{version}/{id}/municipio/getMunicipio")
    @Operation(
            summary = "Buscar municipio pelo ID",
            description = "Busca um municipio no sistema pelo ID fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipio encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O ID fornecido não corresponde a nenhum municipio cadastrado."),
    })
    public Response getMunicipio(@PathVariable Integer id, @PathVariable String version) throws NullPointerException {
        return MunicipiosUseCase.getMunicipio(MethodVersion.fromValue(version), id);
    }

    @PutMapping("/{version}/municipio/update")
    @Operation(
            summary = "Atualizar dados do municipio",
            description = "Atualiza os dados de um municipio fornecido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do municipio atualizados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados de atualização são inválidos."),
    })
    public Response updateMunicipio(@PathVariable String version, @RequestBody MunicipiosDto municipio) throws ValidationException {
        return MunicipiosUseCase.updateMunicipio(MethodVersion.fromValue(version), municipio);
    }

    @GetMapping("/{version}/{uf}/municipio/getListByUf")
    @Operation(
            summary = "Listar todos os municipios por unidade federativa (UF)",
            description = "Lista todos os municipios cadastrados pela unidade federativa informada"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Municipios do estado listados com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição dos municipios."),
    })
    public Response getListMunicipiosByUf(@PathVariable String version, @PathVariable String uf) throws NullPointerException {
        return MunicipiosUseCase.getListaMunicipiosByUf(MethodVersion.fromValue(version), uf);
    }
}
