package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;

import java.util.List;

public class VendedoresDaoImp extends Repository implements VendedoresDao {

    public VendedoresDaoImp(IConnection connection) {
        super(connection, VendedoresDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public void insert(Vendedores produto) throws Exception {

    }

    @Override
    public boolean update(Vendedores produto) throws Exception {
        return false;
    }

    @Override
    public Vendedores getVendedor(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Vendedores> getListVendedores() throws Exception {
        return List.of();
    }
}
