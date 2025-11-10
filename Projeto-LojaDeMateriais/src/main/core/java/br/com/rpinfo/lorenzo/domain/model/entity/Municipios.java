package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MunicipiosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "municipios", prefix = "muni_")
public class Municipios extends EntityClass implements Serializable {

    private Numerico codigo = new Numerico(true);
    private Descricao nome = new Descricao(true);
    private Descricao uf = new Descricao(true);

    public Municipios() {
        super();
    }

    public Municipios(Boolean autoEnableFields){
        super();
    }

    public MunicipiosDto toDto(){
        MunicipiosDto municipioDto = new MunicipiosDto();
        municipioDto.setCodigo(this.getCodigo().getValue());
        municipioDto.setNome(this.getNome().getValue());
        municipioDto.setUnidadeFederativa(this.getUf().getValue());
        return municipioDto;
    }
}
