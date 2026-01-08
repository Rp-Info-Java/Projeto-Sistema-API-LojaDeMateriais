package br.com.rpinfo.lorenzo.core.domain.repositories.relatorios;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosD;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Vendedores;

import java.util.List;

public class RelatoriosDaoImp extends Repository implements RelatoriosDao{

    public RelatoriosDaoImp(IConnection connection) {
        super(connection, RelatoriosDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public List<MovProdutosC> getEntradas() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .where("mvpc_es", "=", "E");

        return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
    }

    @Override
    public MovProdutosC getMovimentacaoEntraE(String transaction) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .where("mvpc_transacao", "=", transaction)
                .and("mvpc_es", "=", "E");

        List<MovProdutosC> list = this.getManager().queryFactory(sql, MovProdutosC.class);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<MovProdutosC> getSaidas() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .where("mvpc_es", "=", "S");

        return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
    }

    //Ao cancelar a movimentacaoCabecalho, automaticamente cancela a movimentacaoDetalhamento
    @Override
    public List<MovProdutosC> getCanceladas() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .where("mvpc_status", "=", "C");

        return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
    }

    @Override
    public List<Vendedores> getComissoes() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Vendedores.class);

        return this.getManager().queryFactory(sql.build(), Vendedores.class);
    }
}
