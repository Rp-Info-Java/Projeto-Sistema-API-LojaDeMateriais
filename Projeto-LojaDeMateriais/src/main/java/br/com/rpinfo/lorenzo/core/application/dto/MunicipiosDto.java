package br.com.rpinfo.lorenzo.core.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Municipios;

@Schema(description = "Dados do município")
@Getter
@Setter
public class MunicipiosDto  extends BaseDto {
    private Integer codigo;
    @Schema(description = "Nome do município", example = "Pindamonhangaba")
    private String nome;
    @Length(max = 2)
    @Schema(description = "Unidade federativa do município", example = "SP")
    private String unidadeFederativa;

    public MunicipiosDto(){
        super();
    }

    public MunicipiosDto(Municipios municipio){
        this.codigo = municipio.getCodigo().getValue();
        this.nome = municipio.getNome().getValue();
        this.unidadeFederativa = municipio.getUf().getValue();
    }

    public Municipios toEntity(){
        Municipios municipio = new Municipios(false);
        municipio.getCodigo().setValue(this.getCodigo());
        municipio.getNome().setValue(this.getNome());
        municipio.getUf().setValue(this.getUnidadeFederativa());
        return municipio;
    }
}
