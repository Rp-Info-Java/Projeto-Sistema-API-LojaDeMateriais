package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.ProdutosMovimentacoes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProdutosMovimentacoesDto extends BaseDto {
    private Integer codigo;
    private String descricao;
    private String marca;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dataMvto;
    private Integer qtde;
    private Double precoCompra;
    private Double total;
    private Double calculo;

    public ProdutosMovimentacoesDto() {
        super();
    }

    public ProdutosMovimentacoesDto(ProdutosMovimentacoes prodMov) {
        this.codigo = prodMov.getCodigo().getValue();
        this.descricao = prodMov.getDescricao().getValue();
        this.marca = prodMov.getMarca().getValue();
        this.dataMvto = prodMov.getDataMvto().getValue();
        this.qtde = prodMov.getQtde().getValue();
        this.precoCompra = prodMov.getPrecoCompra().getValue();
        this.total = prodMov.getTotal().getValue();
        this.calculo = prodMov.getCalculo().getValue();
    }

    public ProdutosMovimentacoes toEntity(){
        ProdutosMovimentacoes prodMov = new ProdutosMovimentacoes(false);
        prodMov.getCodigo().setValue(this.getCodigo());
        prodMov.getDescricao().setValue(this.getDescricao());
        prodMov.getMarca().setValue(this.getMarca());
        prodMov.getDataMvto().setValue(this.getDataMvto());
        prodMov.getQtde().setValue(this.getQtde());
        prodMov.getPrecoCompra().setValue(this.getPrecoCompra());
        prodMov.getTotal().setValue(this.getTotal());
        prodMov.getCalculo().setValue(this.getCalculo());
        return prodMov;
    }
}
