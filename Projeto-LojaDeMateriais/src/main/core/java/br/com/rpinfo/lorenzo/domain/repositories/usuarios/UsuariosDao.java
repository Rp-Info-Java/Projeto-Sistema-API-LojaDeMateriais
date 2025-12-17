package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.usuarios;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;
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
