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

    public MovProdutosD toEntity(){
        MovProdutosD mvpd = new MovProdutosD();
        mvpd.setTransacao(this.transacao);
        mvpd.setStatus(this.status);
        mvpd.setProd_codigo(this.codigoProduto);
        mvpd.setQtde(this.quantidade);
        mvpd.setValordesc(this.valorDesc);
        mvpd.setValoracres(this.valorAcrescimo);
        mvpd.setValoroutros(this.valorOutros);
        mvpd.setValortotal(this.valorTotal);
        return mvpd;
    }
}
