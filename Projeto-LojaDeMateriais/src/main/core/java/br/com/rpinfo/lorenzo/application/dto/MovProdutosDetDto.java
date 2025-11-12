package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;

@Getter
@Setter
public class MovProdutosDetDto extends BaseDto {
    private String transacao;
    @Length(max = 1)
    private String status;
    private Integer codigoProduto;
    private Double quantidade;
    private Double valorDesc;
    private Double valorAcrescimo;
    private Double valorOutros;
    private Double valorTotal;

    public MovProdutosDetDto(){
        super();
    }

    public MovProdutosDetDto(MovProdutosD mvpd){
        this.transacao = mvpd.getTransacao().getValue();
        this.status = mvpd.getStatus().getValue();
        this.codigoProduto = mvpd.getProd_codigo().getValue();
        this.quantidade = mvpd.getQtde().getValue();
        this.valorDesc = mvpd.getValordesc().getValue();
        this.valorAcrescimo = mvpd.getValoracres().getValue();
        this.valorOutros = mvpd.getValoroutros().getValue();
        this.valorTotal = mvpd.getValortotal().getValue();
    }

    public MovProdutosD toEntity(){
        MovProdutosD mvpd = new MovProdutosD();

        mvpd.getTransacao().setValue(this.getTransacao());
        mvpd.getStatus().setValue(this.getStatus());
        mvpd.getProd_codigo().setValue(this.getCodigoProduto());
        mvpd.getQtde().setValue(this.getQuantidade());
        mvpd.getValordesc().setValue(this.getValorDesc());
        mvpd.getValoracres().setValue(this.getValorAcrescimo());
        mvpd.getValoroutros().setValue(this.getValorOutros());
        mvpd.getValortotal().setValue(this.getValorTotal());
        return mvpd;
    }
}
