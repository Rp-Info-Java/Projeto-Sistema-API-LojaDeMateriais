package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.com.rpinfo.lorenzo.core.application.dto.VendComissoesDto;
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
//Nunca usar para Insert, Update ou Delete
@TableAnnotation(tableName = "movprodutosc", prefix = "")
public class VendComissoes extends EntityClass implements Serializable {
    private Numerico vend_codigo = new Numerico(true);
    private Descricao vend_nome = new Descricao(true);
    private Decimal vend_comissao = new Decimal(true);
    private Descricao status = new Descricao(true);
    private Descricao es = new Descricao(true);
    private Decimal soma = new Decimal(true);
    private Decimal comissaoCalculada = new Decimal(true);

    public VendComissoes() { super(); }

    public VendComissoes(Boolean autoEnableFields) { super(); }

    public VendComissoesDto toDto(){
        VendComissoesDto vendComDto = new VendComissoesDto();
        vendComDto.setCodigo(this.getVend_codigo().getValue());
        vendComDto.setNome(this.getVend_nome().getValue());
        vendComDto.setVendComissao(this.getVend_comissao().getValue());
        vendComDto.setStatus(this.getStatus().getValue());
        vendComDto.setEs(this.getEs().getValue());
        vendComDto.setSoma(this.getSoma().getValue());
        vendComDto.setComissao(this.getComissaoCalculada().getValue());
        return vendComDto;
    }
}
