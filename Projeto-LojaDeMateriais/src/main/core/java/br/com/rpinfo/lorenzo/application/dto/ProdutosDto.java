package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;

import java.util.Date;

@Schema(description = "Dados do produto")
@Getter
@Setter
public class ProdutosDto  extends BaseDto {
    private Integer codigo;
    @Schema(description = "Descrição do produto", example = "Produto Teste")
    private String descricao;
    @Schema(description = "Marca do Produto", example = "Kellogs")
    private String marca;
    @Schema(description = "Grupo do Produto", example = "Grupo Parati")
    private String grupo;
    @Schema(description = "Departamento do Produto", example = "Departamento Parati")
    private String departamento;
    @Length(max = 2)
    @Schema(description = "Embalagem do Produto", example = "2")
    private String embalagem;
    @Schema(description = "Quantia de embalagem do Produto", example = "2")
    private Integer quantiaEmbalagem;
    @Schema(description = "Preço de venda do Produto", example = "10")
    private Double precoVenda;
    @Schema(description = "Preço de compra do Produto", example = "10")
    private Double precoCompra;
    @Schema(description = "Quantia de estoque disponível para o produto", example = "10")
    private Double estoque;
    @Schema(description = "Data da última compra (Não deve ser preenchida)")
    private Date dataUltimaCompra;
    @Schema(description = "Data da última venda (Não deve ser preenchida)")
    private Date dataUltimaVenda;

    public ProdutosDto(){
        super();
    }

    public ProdutosDto(Produtos produtos){
        this.codigo = produtos.getCodigo().getValue();
        this.descricao = produtos.getDescricao().getValue();
        this.marca = produtos.getMarca().getValue();
        this.grupo = produtos.getGrupo().getValue();
        this.departamento = produtos.getDpto().getValue();
        this.embalagem = produtos.getEmbalagem().getValue();
        this.quantiaEmbalagem = produtos.getQembalagem().getValue();
        this.precoVenda = produtos.getPreconvenda().getValue();
        this.precoCompra = produtos.getPrecocompra().getValue();
        this.estoque = produtos.getEstoque().getValue();
        this.dataUltimaCompra = produtos.getDtultcompra().getValue();
        this.dataUltimaVenda = produtos.getDtultvenda().getValue();
    }

    public Produtos toEntity(){
        Produtos produto = new Produtos(false);
        produto.getCodigo().setValue(this.getCodigo());
        produto.getDescricao().setValue(this.getDescricao());
        produto.getMarca().setValue(this.getMarca());
        produto.getGrupo().setValue(this.getGrupo());
        produto.getDpto().setValue(this.getDepartamento());
        produto.getEmbalagem().setValue(this.getEmbalagem());
        produto.getQembalagem().setValue(this.getQuantiaEmbalagem());
        produto.getPreconvenda().setValue(this.getPrecoVenda());
        produto.getPrecocompra().setValue(this.getPrecoCompra());
        produto.getEstoque().setValue(this.getEstoque());
        produto.getDtultcompra().setValue(this.getDataUltimaCompra());
        produto.getDtultvenda().setValue(this.getDataUltimaVenda());
        return produto;
    }
}
