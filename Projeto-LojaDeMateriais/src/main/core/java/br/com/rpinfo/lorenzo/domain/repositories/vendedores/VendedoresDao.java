package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedoresDao{
    void insert (Vendedores produto) throws Exception;

    boolean update (Vendedores produto) throws Exception;

    Vendedores getVendedor (Long id) throws Exception;

    List<Vendedores> getListVendedores () throws Exception;
}
