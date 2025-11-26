package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosDetDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Decimal;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "movprodutosd", prefix = "mvpd_")
public class MovProdutosD extends EntityClass implements Serializable {

    private Descricao transacao = new Descricao(true);
    private Descricao status = new Descricao(true);
    private Numerico prod_codigo = new Numerico(true);
    private Decimal qtde = new Decimal(true);
    private Decimal valordesc = new Decimal(true);
    private Decimal valoracres = new Decimal(true);
    private Decimal valoroutros = new Decimal(true);
    private Decimal valortotal = new Decimal(true);

    public MovProdutosD(){ super(); }

    public MovProdutosD(Boolean autoEnableFields){ super(); }

    public MovProdutosDetDto toDto(){
        MovProdutosDetDto dto = new MovProdutosDetDto();
        dto.setTransacao(this.getTransacao().getValue());
        dto.setStatus(this.getStatus().getValue());
        dto.setCodigoProduto(this.getProd_codigo().getValue());
        dto.setQuantidade(this.getQtde().getValue());
        dto.setValorDesc(this.getValordesc().getValue());
        dto.setValorAcrescimo(this.getValoracres().getValue());
        dto.setValorOutros(this.getValoroutros().getValue());
        dto.setValorTotal(this.getValortotal().getValue());
        return dto;
    }
}
