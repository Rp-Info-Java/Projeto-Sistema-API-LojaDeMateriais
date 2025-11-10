package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.usuarios;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;

import java.util.List;

public class UsuariosDaoImp extends Repository implements UsuariosDao {

    public UsuariosDaoImp(IConnection connection) {
        super(connection, UsuariosDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public void insert(Usuarios usuarios) throws Exception {

    }

    @Override
    public boolean update(Usuarios usuarios) throws Exception {
        return false;
    }

    @Override
    public Usuarios getUsuario(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Usuarios> getListUsuarios() throws Exception {
        return List.of();
    }
}
