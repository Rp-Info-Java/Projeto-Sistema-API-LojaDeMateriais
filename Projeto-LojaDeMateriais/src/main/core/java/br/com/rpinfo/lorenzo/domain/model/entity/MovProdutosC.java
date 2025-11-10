package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableAnnotation(tableName = "movprodutosc", prefix = "mvpc_")
public class MovProdutosC {
    private String transacao;
    private String numdcto;
    private String datamvto;
    private String status;
    private String es;
    private String tipoentidade;
    private Integer codentidade;
    private Integer vend_condigo;
    private Double totalprod;
    private Double totaldesc;
    private Double totalacres;
    private Double totaloutros;
    private Double totaldcto;

    public MovProdutosC(){ super(); }
}
