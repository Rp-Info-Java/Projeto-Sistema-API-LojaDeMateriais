package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;

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
                .select("vend_comissao")
                .from(Vendedores.class);

        return this.getManager().queryFactory(sql.build(), Vendedores.class);
    }
}
