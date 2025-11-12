package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.usuarios;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosDao{
    boolean insert (Usuarios usuarios) throws Exception;

    boolean update (Usuarios usuarios) throws Exception;

    Usuarios getUsuario (Integer id) throws Exception;

    List<Usuarios> getListUsuarios () throws Exception;
}
