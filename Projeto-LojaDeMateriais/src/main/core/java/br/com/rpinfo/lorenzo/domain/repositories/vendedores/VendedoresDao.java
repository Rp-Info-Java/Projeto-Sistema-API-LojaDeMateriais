package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;
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
