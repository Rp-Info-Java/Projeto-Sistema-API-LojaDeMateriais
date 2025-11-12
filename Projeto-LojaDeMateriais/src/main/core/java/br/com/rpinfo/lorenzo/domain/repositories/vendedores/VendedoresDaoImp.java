package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;

import java.sql.ResultSet;
import java.util.List;

public class VendedoresDaoImp extends Repository implements VendedoresDao {

    public VendedoresDaoImp(IConnection connection) {
        super(connection, VendedoresDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private Integer getNextCode() throws Exception {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(vend_codigo) + 1 as vend_codigo")
                .from(Vendedores.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("vend_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insert(Vendedores vendedor) throws Exception {
        vendedor.getCodigo().setValue(this.getNextCode());
        vendedor.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(vendedor);
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
    public boolean update(Vendedores vendedor) throws Exception {
        vendedor.toUpdate("vend_codigo = " + vendedor.getCodigo().getValue());
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(vendedor);
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
    public Vendedores getVendedor(Integer id) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Vendedores.class)
                .where("vend_codigo", "=", id);
        List<Vendedores> list = this.getManager().queryFactory(sql, Vendedores.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Vendedores> getListVendedores() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Vendedores.class)
                .orderBy("vend_codigo");

        return this.getManager().queryFactory(sql.build(), Vendedores.class);
    }
}
