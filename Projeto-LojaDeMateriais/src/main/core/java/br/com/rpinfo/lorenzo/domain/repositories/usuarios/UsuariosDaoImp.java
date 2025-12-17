package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.usuarios;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuariosDaoImp extends Repository implements UsuariosDao {

    public UsuariosDaoImp(IConnection connection) {
        super(connection, UsuariosDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private Integer getNextCode() throws SQLException {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(usua_codigo) + 1 as usua_codigo")
                .from(Usuarios.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("usua_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insert(Usuarios usuarios) throws SQLException {
        usuarios.getCodigo().setValue(this.getNextCode());
        usuarios.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(usuarios);
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
    public boolean update(Usuarios usuarios) throws Exception {
        usuarios.toUpdate("usua_codigo = " + usuarios.getCodigo().getValue());
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(usuarios);
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
    public Usuarios getUsuario(Integer id) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Usuarios.class)
                .where("usua_codigo", "=", id);
        List<Usuarios> list = this.getManager().queryFactory(sql, Usuarios.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Usuarios> getListUsuarios() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")                                           //quando for fazer a pesquisa por campos específicos, sempre lembrar de por a vírgula após o ult. campo
                .from(Usuarios.class)
                .orderBy("usua_codigo");

        return this.getManager().queryFactory(sql.build(), Usuarios.class);
    }
}
