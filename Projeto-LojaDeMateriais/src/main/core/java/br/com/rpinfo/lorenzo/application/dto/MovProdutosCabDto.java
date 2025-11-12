package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;

import java.util.List;

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
    private List<MovProdutosDetDto> itens;

    public MovProdutosCabDto(){
        super();
    }

    public MovProdutosCabDto(MovProdutosC mvpc){
        this.transacao = mvpc.getTransacao().getValue();
        this.numeroDocumento = mvpc.getNumdcto().getValue();
        this.dataMovimento = mvpc.getDatamvto().getValue();
        this.status = mvpc.getStatus().getValue();
        this.entradaSaida = mvpc.getEs().getValue();
        this.tipoEntidade = mvpc.getTipoentidade().getValue();
        this.codigoEntidade = mvpc.getCodentidade().getValue();
        this.codigoVendedor = mvpc.getVend_codigo().getValue();
        this.totalProdutos = mvpc.getTotalprod().getValue();
        this.totalDesc = mvpc.getTotaldesc().getValue();
        this.totalAcrescimo = mvpc.getTotalacres().getValue();
        this.totalOutros = mvpc.getTotaloutros().getValue();
        this.totalDocumento = mvpc.getTotaldcto().getValue();
        this.itens = mvpc.getItens().stream().map(MovProdutosDetDto::new).toList();
    }

    public MovProdutosC toEntity(){
        MovProdutosC mvpc = new MovProdutosC(false);
        mvpc.getTransacao().setValue(this.getTransacao());
        mvpc.getNumdcto().setValue(this.getNumeroDocumento());
        mvpc.getDatamvto().setValue(this.getDataMovimento());
        mvpc.getStatus().setValue(this.getStatus());
        mvpc.getEs().setValue(this.getEntradaSaida());
        mvpc.getTipoentidade().setValue(this.getTipoEntidade());
        mvpc.getCodentidade().setValue(this.getCodigoEntidade());
        mvpc.getVend_codigo().setValue(this.getCodigoVendedor());
        mvpc.getTotalprod().setValue(this.getTotalProdutos());
        mvpc.getTotaldesc().setValue(this.getTotalDesc());
        mvpc.getTotalacres().setValue(this.getTotalAcrescimo());
        mvpc.getTotaloutros().setValue(this.getTotalOutros());
        mvpc.getTotaldcto().setValue(this.getTotalDocumento());
        mvpc.setItens(this.itens.stream().map(MovProdutosDetDto::toEntity).toList());
        return mvpc;
    }
}
