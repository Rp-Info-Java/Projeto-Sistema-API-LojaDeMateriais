package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;

import java.util.Date;

@Getter
@Setter
public class ProdutosDto  extends BaseDto {
    private Integer codigo;
    private String descricao;
    private String marca;
    private String grupo;
    private String departamento;
    @Length(max = 2)
    private String embalagem;
    private Integer quantiaEmbalagem;
    private Double precoVenda;
    private Double precoCompra;
    private Double estoque;
    private Date dataUltimaCompra;
    private Date dataUltimaVenda;

    public ProdutosDto(){
        super();
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
