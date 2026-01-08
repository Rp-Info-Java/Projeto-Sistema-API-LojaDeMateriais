package br.com.rpinfo.lorenzo.core.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;

import java.util.Date;
import java.util.List;

@Schema(description = "Dados do cabeçalho de movimentação de produtos")
@Getter
@Setter
public class MovProdutosCabDto extends BaseDto {
    private String transacao;
    @Length(max = 10)
    @Schema(description = "Número do documento", example = "123456789")
    private String numeroDocumento;
    @Length(max = 8)
    @Schema(description = "Data do movimento", example = "2022-01-01")
    private Date dataMovimento;
    @Length(max = 1)
    @Schema(description = "Status da movimentação (N ou C)", example = "N")
    private String status;
    @Length(max = 1)
    @Schema(description = "Entrada ou saída (E ou S)", example = "E")
    private String entradaSaida;
    @Length(max = 1)
    @Schema(description = "Tipo de entidade (F ou J)", example = "J")
    private String tipoEntidade;
    @Schema(description = "Código da entidade", example = "1")
    private Integer codigoEntidade;
    @Schema(description = "Código de vendedor", example = "1")
    private Integer codigoVendedor;
    @Schema(description = "Valor total de Produtos", example = "1000")
    private Double totalProdutos;
    @Schema(description = "Valor total de desconto", example = "100")
    private Double totalDesc;
    @Schema(description = "Valor total de acréscimo", example = "100")
    private Double totalAcrescimo;
    @Schema(description = "Valor total outros", example = "299")
    private Double totalOutros;
    @Schema(description = "Valor total do documento", example = "1299")
    private Double totalDocumento;
    @Schema(description = "Lista de produtos da movimentação")
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
