package br.com.rpinfo.lorenzo.core.domain.repositories.usuarios;

import br.com.rpinfo.lorenzo.core.domain.model.entity.Usuarios;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UsuariosDao{
    boolean insert (Usuarios usuarios) throws SQLException;

    boolean update (Usuarios usuarios) throws Exception;

    Usuarios getUsuario (Integer id) throws Exception;

    List<Usuarios> getListUsuarios () throws Exception;
}
