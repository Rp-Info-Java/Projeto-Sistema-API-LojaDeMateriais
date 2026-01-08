package br.com.rpinfo.lorenzo.core.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosD;

@Schema(description = "Dados do detalhamento de movimentações do produto")
@Getter
@Setter
public class MovProdutosDetDto extends BaseDto {
    private String transacao;
    @Length(max = 1)
    @Schema(description = "Status do movimento (N ou C)", example = "N")
    private String status;
    @Schema(description = "Codigo do produto", example = "1")
    private Integer codigoProduto;
    @Schema(description = "Quantidade do produto", example = "1")
    private Double quantidade;
    private Double valorDesc;
    @Schema(description = "Valor do acréscimo", example = "1")
    private Double valorAcrescimo;
    @Schema(description = "Valor outros", example = "100")
    private Double valorOutros;
    @Schema(description = "Valor total", example = "100")
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
