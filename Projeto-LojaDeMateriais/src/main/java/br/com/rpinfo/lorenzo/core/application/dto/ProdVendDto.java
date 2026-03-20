package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.ProdVend;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProdVendDto extends BaseDto {
    private Integer codigo;
    private Integer qtde;
    private String es;
    private String descricao;
    private String marca;
    private Double precoVenda;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date datamvto;
    private Double total;

    public ProdVendDto() { super(); }

    public ProdVendDto(ProdVend pv) {
        this.codigo = pv.getProd_codigo().getValue();
        this.qtde = pv.getMvpd_qtde().getValue();
        this.es = pv.getMvpc_es().getValue();
        this.descricao = pv.getProd_descricao().getValue();
        this.marca = pv.getProd_marca().getValue();
        this.precoVenda = pv.getProd_preconvenda().getValue();
        this.datamvto = pv.getMvpc_datamvto().getValue();
        this.total = pv.getTotal().getValue();
    }

    public ProdVend toEntity() {
        ProdVend pv = new ProdVend(false);
        pv.getProd_codigo().setValue(this.getCodigo());
        pv.getMvpd_qtde().setValue(this.getQtde());
        pv.getMvpc_es().setValue(this.getEs());
        pv.getProd_descricao().setValue(this.getDescricao());
        pv.getProd_marca().setValue(this.getMarca());
        pv.getProd_preconvenda().setValue(this.getPrecoVenda());
        pv.getMvpc_datamvto().setValue(this.getDatamvto());
        pv.getTotal().setValue(this.getTotal());
        return pv;
    }
}
