package br.com.rpinfo.lorenzo.core.domain.repositories.pendfin;

import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.PendFin;
import br.framework.classes.DataBase.QueryBuilder;
import br.framework.classes.DataBase.Repository;
import br.framework.classes.DataBase.Transaction;
import br.framework.interfaces.IConnection;

import java.util.List;

public class PendFinDaoImp extends Repository implements PendFinDao {
    public PendFinDaoImp(IConnection connection){
        super(connection, PendFinDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private String getNextTransaction() throws Exception {
        try {
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(PendFin.class)
                    .orderBy("pfin_transacao DESC", true);

            List<PendFin> lista = this.getManager().queryFactory(sql, PendFin.class);
            if (!lista.isEmpty()) {
                long transacao = Long.parseLong(lista.get(0).getTransacao().getValue());
                return String.format("%08d", transacao + 1);
            }
            return "00000001";
        } catch (Exception e) {
            throw new ValidationException("Erro buscando próxima transação: " + e.getMessage());
        }
    }

    @Override
    public boolean insertPendFin(PendFin pfin) throws Exception {
        pfin.getTransacao().setValue(this.getNextTransaction());
        pfin.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(pfin);
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
}
