package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;

import java.util.List;

public class RelatoriosDaoImp extends Repository implements RelatoriosDao{

    public RelatoriosDaoImp(IConnection connection) {
        super(connection, RelatoriosDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public List<MovProdutosC> getEntradas() throws Exception {
        return List.of();
    }

    @Override
    public List<MovProdutosC> getSaidas() throws Exception {
        return List.of();
    }

    @Override
    public List<MovProdutosC> getCanceladas() throws Exception {
        return List.of();
    }

    @Override
    public List<Vendedores> getComissoes() throws Exception {
        return List.of();
    }
}
