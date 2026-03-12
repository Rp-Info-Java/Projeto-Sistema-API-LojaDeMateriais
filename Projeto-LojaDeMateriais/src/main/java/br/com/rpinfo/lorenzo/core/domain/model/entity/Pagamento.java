package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.com.rpinfo.lorenzo.core.application.dto.PagamentoDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Decimal;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pagamento extends EntityClass implements Serializable {
    private Descricao transacao = new Descricao(true);
    private Descricao flagES = new Descricao(true);
    private Descricao formaPgto = new Descricao(true);
    private Decimal totDcto = new Decimal(true);
    private Descricao dataMvto = new Descricao(true);
    private Numerico codEntidade = new Numerico(true);
    private Descricao config = new Descricao(true);
    private List<String> dataVcto = new ArrayList<>();
    private List<String> parcelas = new ArrayList<>();
    private List<String> valTotal = new ArrayList<>();

    public Pagamento(){ super(); }

    public Pagamento(Boolean autoEnableFields){ super(); }

    public PagamentoDto toDto(){
        PagamentoDto dto = new PagamentoDto();
        dto.setTransacao(this.getTransacao().getValue());
        dto.setFlagES(this.getFlagES().getValue());
        dto.setFormaPgto(this.getFormaPgto().getValue());
        dto.setTotDcto(this.getTotDcto().getValue());
        dto.setDataMvto(this.getDataMvto().getValue());
        dto.setCodEntidade(this.getCodEntidade().getValue());
        dto.setConfig(this.getConfig().getValue());
        dto.setDataVcto(this.getDataVcto());
        dto.setParcelas(this.getParcelas());
        dto.setValTotal(this.getValTotal());
        return dto;
    }

   /* public Pagamento(int size) {

        this.dataVcto = new ArrayList<>(size);
        this.parcelas = new ArrayList<>(size);
        this.valTotal = new ArrayList<>(size);
    }*/
}
