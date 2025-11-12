package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.VendedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Decimal;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

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
