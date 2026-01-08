package br.com.rpinfo.lorenzo.core.domain.repositories.vendedores;

import br.com.rpinfo.lorenzo.core.domain.model.entity.Vendedores;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface VendedoresDao{
    boolean insert (Vendedores vendedor) throws SQLException;

    boolean update (Vendedores vendedor) throws Exception;

    Vendedores getVendedor (Integer id) throws Exception;

    List<Vendedores> getListVendedores () throws Exception;
}
