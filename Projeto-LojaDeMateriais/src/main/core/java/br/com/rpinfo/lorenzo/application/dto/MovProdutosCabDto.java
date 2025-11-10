package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;

@Getter
@Setter
public class MovProdutosCabDto extends BaseDto {
    private String transacao;
    @Length(max = 10)
    private String numeroDocumento;
    @Length(max = 8)
    private String dataMovimento;
    @Length(max = 1)
    private String status;
    @Length(max = 1)
    private String entradaSaida;
    @Length(max = 1)
    private String tipoEntidade;
    private Integer codigoEntidade;
    private Integer codigoVendedor;
    private Double totalProdutos;
    private Double totalDesc;
    private Double totalAcrescimo;
    private Double totalOutros;
    private Double totalDocumento;

    public MovProdutosCabDto(){
        super();
    }

    public MovProdutosC toEntity(){
        MovProdutosC mvpc = new MovProdutosC();
        mvpc.setTransacao(this.transacao);
        mvpc.setNumdcto(this.numeroDocumento);
        mvpc.setDatamvto(this.dataMovimento);
        mvpc.setStatus(this.status);
        mvpc.setEs(this.entradaSaida);
        mvpc.setTipoentidade(this.tipoEntidade);
        mvpc.setCodentidade(this.codigoEntidade);
        mvpc.setVend_condigo(this.codigoVendedor);
        mvpc.setTotalprod(this.totalProdutos);
        mvpc.setTotaldesc(this.totalDesc);
        mvpc.setTotalacres(this.totalAcrescimo);
        mvpc.setTotaloutros(this.totalOutros);
        mvpc.setTotaldcto(this.totalDocumento);
        return mvpc;
    }
}
