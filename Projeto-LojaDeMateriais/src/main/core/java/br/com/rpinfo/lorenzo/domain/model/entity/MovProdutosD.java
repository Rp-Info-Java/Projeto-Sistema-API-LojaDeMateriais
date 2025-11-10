package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableAnnotation(tableName = "movprodutosd", prefix = "mvpd_")
public class MovProdutosD {
    private String transacao;
    private String status;
    private Integer prod_codigo;
    private Double qtde;
    private Double valordesc;
    private Double valoracres;
    private Double valoroutros;
    private Double valortotal;

    public MovProdutosD(){ super(); }
}
