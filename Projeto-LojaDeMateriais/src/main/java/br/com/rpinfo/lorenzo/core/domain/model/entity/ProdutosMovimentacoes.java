package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.com.rpinfo.lorenzo.core.application.dto.ProdutosMovimentacoesDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Data;
import br.com.rpinfo.lorenzo.core.domain.model.field.Decimal;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;
import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "produtos", prefix = "")
public class ProdutosMovimentacoes extends EntityClass implements Serializable {
    private Numerico codigo = new Numerico(true);
    private Descricao descricao = new Descricao(true);
    private Descricao marca = new Descricao(true);
    private Data dataMvto = new Data(true);
    private Numerico qtde = new Numerico(true);
    private Decimal precoCompra = new Decimal(true);
    private Decimal total = new Decimal(true);
    private Decimal calculo = new Decimal(true);

    public ProdutosMovimentacoes() {
        super();
    }

    public ProdutosMovimentacoes(Boolean autoEnableFields){ super(); }

    public ProdutosMovimentacoesDto toDto(){
        ProdutosMovimentacoesDto prodMovDto = new ProdutosMovimentacoesDto();
        prodMovDto.setCodigo(this.getCodigo().getValue());
        prodMovDto.setDescricao(this.getDescricao().getValue());
        prodMovDto.setMarca(this.getMarca().getValue());
        prodMovDto.setDataMvto(this.getDataMvto().getValue());
        prodMovDto.setQtde(this.getQtde().getValue());
        prodMovDto.setPrecoCompra(this.getPrecoCompra().getValue());
        prodMovDto.setTotal(this.getTotal().getValue());
        prodMovDto.setCalculo(this.getCalculo().getValue());
        return prodMovDto;
    }
}
