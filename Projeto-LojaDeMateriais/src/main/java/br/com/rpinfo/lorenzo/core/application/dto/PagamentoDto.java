package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Pagamento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagamentoDto extends BaseDto {
    private String transacao;
    private String flagES;
    private String formaPgto;
    private Double totDcto;
    private String dataMvto;
    private Integer codEntidade;
    private String config;
    private List<String> dataVcto;
    private List<String> parcelas;
    private List<String> valTotal;

    public PagamentoDto() { super(); }

    public PagamentoDto(Pagamento pgto){
        this.transacao = pgto.getTransacao().getValue();
        this.flagES = pgto.getFlagES().getValue();
        this.formaPgto = pgto.getFormaPgto().getValue();
        this.totDcto = pgto.getTotDcto().getValue();
        this.dataMvto = pgto.getDataMvto().getValue();
        this.codEntidade = pgto.getCodEntidade().getValue();
        this.config = pgto.getConfig().getValue();
        this.dataVcto = pgto.getDataVcto();
        this.parcelas = pgto.getParcelas();
        this.valTotal = pgto.getValTotal();
    }

    public Pagamento toEntity(){
        Pagamento pgto = new Pagamento(false);
        pgto.getTransacao().setValue(this.getTransacao());
        pgto.getFlagES().setValue(this.getFlagES());
        pgto.getFormaPgto().setValue(this.getFormaPgto());
        pgto.getTotDcto().setValue(this.getTotDcto());
        pgto.getDataMvto().setValue(this.getDataMvto());
        pgto.getCodEntidade().setValue(this.getCodEntidade());
        pgto.getConfig().setValue(this.getConfig());
        pgto.setDataVcto(this.getDataVcto());
        pgto.setParcelas(this.getParcelas());
        pgto.setValTotal(this.getValTotal());
        return pgto;
    }
}
