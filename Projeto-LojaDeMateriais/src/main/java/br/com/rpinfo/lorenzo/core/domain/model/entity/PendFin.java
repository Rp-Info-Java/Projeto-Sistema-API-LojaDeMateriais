package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.com.rpinfo.lorenzo.core.application.dto.PendFinDto;
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
@TableAnnotation(tableName = "Pendfin", prefix = "pfin_")
public class PendFin extends EntityClass implements Serializable {
    private Descricao transacao = new Descricao(true);
    private Descricao status = new Descricao(true);
    private Descricao catentidade = new Descricao(true);
    private Numerico codentidade = new Numerico(true);
    private Descricao tipo = new Descricao(true);
    private Descricao documento = new Descricao(true);
    private Decimal valor = new Decimal(true);
    private Decimal parcela = new Decimal(true);
    private Data datavcto = new Data(true);
    private Descricao origem = new Descricao(true);

    public PendFin() { super(); }

    public PendFin(Boolean autoEnableFields) { super(); }

    public PendFinDto toDto(){
        PendFinDto pfinDto = new PendFinDto();
        pfinDto.setTransacao(this.getTransacao().getValue());
        pfinDto.setStatus(this.getStatus().getValue());
        pfinDto.setCatentidade(this.getCatentidade().getValue());
        pfinDto.setCodEntidade(this.getCodentidade().getValue());
        pfinDto.setTipo(this.getTipo().getValue());
        pfinDto.setDocumento(this.getDocumento().getValue());
        pfinDto.setValor(this.getValor().getValue());
        pfinDto.setParcela(this.getParcela().getValue());
        pfinDto.setDatavcto(this.getDatavcto().getValue());
        pfinDto.setOrigem(this.getOrigem().getValue());
        return pfinDto;
    }
}
