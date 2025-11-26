package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Decimal;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@TableAnnotation(tableName = "movprodutosc", prefix = "mvpc_")
public class MovProdutosC extends EntityClass implements Serializable {

    private Descricao transacao = new Descricao(true);
    private Descricao numdcto = new Descricao(true);
    private Descricao datamvto = new Descricao(true);
    private Descricao status = new Descricao(true);
    private Descricao es = new Descricao(true);
    private Descricao tipoentidade = new Descricao(true);
    private Numerico codentidade = new Numerico(true);
    private Numerico vend_codigo = new Numerico(true);
    private Decimal totalprod = new Decimal(true);
    private Decimal totaldesc = new Decimal(true);
    private Decimal totalacres = new Decimal(true);
    private Decimal totaloutros = new Decimal(true);
    private Decimal totaldcto = new Decimal(true);
    private List<MovProdutosD> itens = new ArrayList<>();

    public MovProdutosC(){ super(); }

    public MovProdutosC(Boolean autoEnableFields) { super(); }

    public MovProdutosCabDto toDto(){
        MovProdutosCabDto dto = new MovProdutosCabDto();
        dto.setTransacao(this.getTransacao().getValue());
        dto.setNumeroDocumento(this.getNumdcto().getValue());
        dto.setDataMovimento(this.getDatamvto().getValue());
        dto.setStatus(this.getStatus().getValue());
        dto.setEntradaSaida(this.getEs().getValue());
        dto.setTipoEntidade(this.getTipoentidade().getValue());
        dto.setCodigoEntidade(this.getCodentidade().getValue());
        dto.setCodigoVendedor(this.getVend_codigo().getValue());
        dto.setTotalProdutos(this.getTotalprod().getValue());
        dto.setTotalDesc(this.getTotaldesc().getValue());
        dto.setTotalAcrescimo(this.getTotalacres().getValue());
        dto.setTotalOutros(this.getTotaloutros().getValue());
        dto.setTotalDocumento(this.getTotaldcto().getValue());
        dto.setItens(this.getItens().stream().map(MovProdutosD::toDto).toList());
        return dto;
    }
}
