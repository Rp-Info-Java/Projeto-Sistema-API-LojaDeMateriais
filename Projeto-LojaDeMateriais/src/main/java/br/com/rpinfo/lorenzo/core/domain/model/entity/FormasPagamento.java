package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.com.rpinfo.lorenzo.core.application.dto.FormasPagamentoDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "Formaspagamento", prefix = "fpgt_")
public class FormasPagamento extends EntityClass implements Serializable {
    private Descricao codigo = new Descricao(true);
    private Descricao descricao = new Descricao(true);
    private Descricao prazos = new Descricao(true);

    public FormasPagamento() { super(); }

    public FormasPagamento(Boolean autoEnableFields) { super(); }

    public FormasPagamentoDto toDto(){
        FormasPagamentoDto fpgtoDto = new FormasPagamentoDto();
        fpgtoDto.setCodigo(this.getCodigo().getValue());
        fpgtoDto.setDescricao(this.getDescricao().getValue());
        fpgtoDto.setPrazos(this.getPrazos().getValue());
        return fpgtoDto;
    }
}
