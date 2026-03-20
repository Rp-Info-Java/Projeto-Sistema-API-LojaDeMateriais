package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.com.rpinfo.lorenzo.core.application.dto.ProdVendDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Data;
import br.com.rpinfo.lorenzo.core.domain.model.field.Decimal;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;
import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "produtos", prefix = "")
public class ProdVend extends EntityClass implements Serializable {

    private Numerico prod_codigo = new Numerico(true);
    private Numerico mvpd_qtde = new Numerico(true);
    private Descricao mvpc_es = new Descricao(true);
    private Descricao prod_descricao = new Descricao(true);
    private Descricao prod_marca = new Descricao(true);
    private Decimal prod_preconvenda = new Decimal(true);
    private Data mvpc_datamvto = new Data(true);
    private Decimal total = new Decimal(true);

    public ProdVend() {
        super();
    }

    public ProdVend(Boolean autoEnableFields) {
        super();
    }

    public ProdVendDto toDto(){
        ProdVendDto dto = new ProdVendDto();
        dto.setCodigo(this.getProd_codigo().getValue());
        dto.setQtde(this.getMvpd_qtde().getValue());
        dto.setEs(this.getMvpc_es().getValue());
        dto.setDescricao(this.getProd_descricao().getValue());
        dto.setMarca(this.getProd_marca().getValue());
        dto.setPrecoVenda(this.getProd_preconvenda().getValue());
        dto.setDatamvto(this.getMvpc_datamvto().getValue());
        dto.setTotal(this.getTotal().getValue());
        return dto;
    }
}
