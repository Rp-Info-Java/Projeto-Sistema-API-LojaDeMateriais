package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import br.framework.classes.helpers.StringList;
import br.framework.classes.helpers.Types;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.application.dto.MovProdutosCabDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Data;
import br.com.rpinfo.lorenzo.core.domain.model.field.Decimal;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@TableAnnotation(tableName = "movprodutosc", prefix = "mvpc_")
public class MovProdutosC extends EntityClass implements Serializable {

    private Descricao transacao = new Descricao(true);
    private Descricao numdcto = new Descricao(true);
    private Data datamvto = new Data(true);
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
    private Cliente cliente = new Cliente();
    private Fornecedores fornecedores = new Fornecedores();
    private Vendedores vendedores = new Vendedores();

    public MovProdutosC(){ this(false); }

    public MovProdutosC(String condition, Boolean autoEnableFields){ super(condition); }

    public MovProdutosC(Boolean autoEnableFields) { super(); setupFields(); }

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

    @Override
    public void setupFields(){
        StringList join = new StringList();
        join.add("mvpc_codentidade = clie_codigo");
        this.codentidade.addRelationEntity(this.cliente, join, Types.JoinType.InnerJoin);

        join = new StringList();
        join.add("mvpc_codentidade = forn_codigo");
        this.codentidade.addRelationEntity(this.fornecedores, join, Types.JoinType.InnerJoin);

    }
}
