package br.com.rpinfo.lorenzo.core.domain.repositories.formaspagamento;

import br.com.rpinfo.lorenzo.core.domain.model.entity.FormasPagamento;
import br.framework.classes.DataBase.QueryBuilder;
import br.framework.interfaces.IConnection;
import br.framework.classes.DataBase.Repository;

import java.util.List;

public class FormasPagamentoDaoImp extends Repository implements FormasPagamentoDao{

    public FormasPagamentoDaoImp(IConnection connection){
        super(connection, FormasPagamentoDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public FormasPagamento getFormasPagamento(String codigo) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(FormasPagamento.class)
            .where("fpgt_codigo", "=", codigo);
        List<FormasPagamento> list = this.getManager().queryFactory(sql, FormasPagamento.class);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
