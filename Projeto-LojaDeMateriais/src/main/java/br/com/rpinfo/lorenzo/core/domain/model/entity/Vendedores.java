package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.application.dto.VendedoresDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Decimal;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "vendedores", prefix = "vend_")
public class Vendedores extends EntityClass implements Serializable {

    private Numerico codigo = new Numerico(true);
    private Descricao nome = new Descricao(true);
    private Decimal comissao = new Decimal(true);

    public Vendedores(){ super(); }

    public Vendedores(Boolean autoEnableFields){
        super();
    }

    public VendedoresDto toDto(){
        VendedoresDto vendDto = new VendedoresDto();
        vendDto.setCodigo(this.getCodigo().getValue());
        vendDto.setNome(this.getNome().getValue());
        vendDto.setComissao(this.getComissao().getValue());
        return vendDto;
    }
}
