package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Data;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Decimal;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "Produtos", prefix = "prod_")
public class Produtos extends EntityClass implements Serializable {

    private Numerico codigo = new Numerico(true);
    private Descricao descricao = new Descricao(true);
    private Descricao marca = new Descricao(true);
    private Descricao grupo = new Descricao(true);
    private Descricao dpto = new Descricao(true);
    private Descricao embalagem = new Descricao(true);
    private Numerico qembalagem = new Numerico(true);
    private Decimal preconvenda = new Decimal(true);
    private Decimal precocompra = new Decimal(true);
    private Decimal estoque = new Decimal(true);
    private Data dtultcompra = new Data(true);
    private Data dtultvenda = new Data(true);

    public Produtos(){ super(); }

    public Produtos(Boolean autoEnableFields){
        super();
    }

    public ProdutosDto toDto(){
        ProdutosDto dto = new ProdutosDto();
        dto.setCodigo(this.getCodigo().getValue());
        dto.setDescricao(this.getDescricao().getValue());
        dto.setMarca(this.getMarca().getValue());
        dto.setGrupo(this.getGrupo().getValue());
        dto.setDepartamento(this.getDpto().getValue());
        dto.setEmbalagem(this.getEmbalagem().getValue());
        dto.setQuantiaEmbalagem(this.getQembalagem().getValue());
        dto.setPrecoVenda(this.getPreconvenda().getValue());
        dto.setPrecoCompra(this.getPrecocompra().getValue());
        dto.setEstoque(this.getEstoque().getValue());
        dto.setDataUltimaCompra(this.getDtultcompra().getValue());
        dto.setDataUltimaVenda(this.getDtultvenda().getValue());
        return dto;
    }
}
