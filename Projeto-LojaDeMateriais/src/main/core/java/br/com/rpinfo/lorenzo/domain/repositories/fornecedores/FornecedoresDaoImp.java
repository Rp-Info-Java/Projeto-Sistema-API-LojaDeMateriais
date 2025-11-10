package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Fornecedores;
import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;

import java.sql.ResultSet;
import java.util.List;

public class FornecedoresDaoImp extends Repository implements FornecedoresDao {

    public FornecedoresDaoImp(IConnection connection) {
        super(connection, FornecedoresDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    //Feito para substituir os SEQUENCES no banco de dados (postgresql)
    private Integer getNextCode() throws Exception {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(forn_codigo) + 1 as forn_codigo")
                .from(Fornecedores.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("forn_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insert(Fornecedores fornecedores) throws Exception {
        fornecedores.getCodigo().setValue(this.getNextCode());
        fornecedores.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(fornecedores);
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
    public List<Fornecedores> getListFornecedores() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Fornecedores.class)
                .orderBy("forn_codigo");

        return this.getManager().queryFactory(sql.build(), Fornecedores.class);
    }

    @Override
    public Fornecedores getFornecedor(Integer id) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Fornecedores.class)
                .where("forn_codigo", "=", id);

        List<Fornecedores> list = this.getManager().queryFactory(sql, Fornecedores.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean update(Fornecedores fornecedores) throws Exception {
        fornecedores.toUpdate("forn_codigo = " + fornecedores.getCodigo().getValue());
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(fornecedores);
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
    public boolean getFornecedorCpfCnpj(String cpfCnpj) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Fornecedores.class)
                .where("forn_cpfcnpj", "=", cpfCnpj);
        List<Fornecedores> list = this.getManager().queryFactory(sql, Fornecedores.class);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }
}
