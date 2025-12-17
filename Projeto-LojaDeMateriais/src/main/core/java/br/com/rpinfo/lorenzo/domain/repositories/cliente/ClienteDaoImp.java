package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.cliente;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDaoImp extends Repository implements ClienteDao {

    public ClienteDaoImp(IConnection connection) {
        super(connection, ClienteDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private Integer getNextCode() throws SQLException {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(clie_codigo) + 1 as clie_codigo")
                .from(Cliente.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("clie_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insertCliente(Cliente cliente) throws SQLException {
        cliente.getCodigo().setValue(this.getNextCode());
        cliente.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(cliente);
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
    public boolean updateCliente(Cliente cliente){
        cliente.toUpdate("clie_codigo = " + cliente.getCodigo().getValue());
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(cliente);
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
    public Cliente getCliente(Integer id) throws Exception{
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Cliente.class)
                .where("clie_codigo", "=", id);
        List<Cliente> list = this.getManager().queryFactory(sql, Cliente.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Cliente> getListClientes() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")                                           //quando for fazer a pesquisa por campos específicos, sempre lembrar de por a vírgula após o ult. campo
                .from(Cliente.class)
                .orderBy("clie_codigo");

        return this.getManager().queryFactory(sql.build(), Cliente.class);
    }

    @Override
    public boolean getClienteCpfCnpj(String cpfCnpj) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Cliente.class)
                .where("clie_cpfcnpj", "=", cpfCnpj);
        List<Cliente> list = this.getManager().queryFactory(sql, Cliente.class);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }
}
