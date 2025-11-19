package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes;

import br.framework.classes.DataBase.QueryBuilder;
import br.framework.classes.DataBase.Repository;
import br.framework.classes.DataBase.Transaction;
import br.framework.interfaces.IEntityClass;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;

import java.util.List;

public class MovimentacoesDaoImp extends Repository implements MovimentacoesDao {

    public MovimentacoesDaoImp(IConnection connection) {
        super(connection, MovimentacoesDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private String getNextTransaction() throws Exception{
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .orderBy("mvpc_transacao DESC", true);

        List<MovProdutosC> lista = this.getManager().queryFactory(sql, MovProdutosC.class);
        if(!lista.isEmpty()){
            long transacao = Long.parseLong(lista.get(0).getTransacao().getValue());
            return String.format("%08d", transacao + 1);
        }
        return "00000001";
    }

    @Override
    public MovProdutosC getMovimentacaoC(String transaction) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .where("mvpc_transacao", "=", transaction);

        List<MovProdutosC> list = this.getManager().queryFactory(sql, MovProdutosC.class);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<MovProdutosD> getMovimentacaoD(String transaction) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosD.class)
                .where("mvpd_transacao", "=", transaction);

        return this.getManager().queryFactory(sql.build(), MovProdutosD.class);
    }

    @Override
    public boolean insertEntradas(MovProdutosC mvpc) throws Exception {
        String transacao = this.getNextTransaction();

        mvpc.getTransacao().setValue(transacao);
        mvpc.getItens().forEach(item -> item.getTransacao().setValue(transacao));
        mvpc.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(mvpc);
            transaction.addEntities((List<IEntityClass>) (List<?>) mvpc.getItens());
            if (transaction.commit()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean insertSaidas(MovProdutosC mvpc, String transacao) throws Exception {
        mvpc.toUpdate("mvpc_transacao = '" + transacao + "'");
        mvpc.getItens().forEach(item -> {item.getTransacao().setValue(transacao);
            item.toUpdate("mvpd_transacao ='" + transacao + "' AND mvpd_prod_codigo = '" + item.getProd_codigo().getValue() + "'");
        });
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(mvpc);
            transaction.addEntities((List<IEntityClass>) (List<?>) mvpc.getItens());

            if(transaction.commit()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean cancelaMovimentacao(MovProdutosC mvpc) throws Exception {
        mvpc.toUpdate("mvpc_transacao = '" + mvpc.getTransacao().getValue() + "'");
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(mvpc);
            transaction.addEntities((List<IEntityClass>) (List<?>) mvpc.getItens());

            if(transaction.commit()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<MovProdutosC> getListMovimentacoesC() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosC.class)
                .orderBy("mvpc_transacao");

        return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
    }

    @Override
    public List<MovProdutosD> getListMovimentacoesD() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(MovProdutosD.class)
                .orderBy("mvpd_transacao");

        return this.getManager().queryFactory(sql.build(), MovProdutosD.class);
    }
}
