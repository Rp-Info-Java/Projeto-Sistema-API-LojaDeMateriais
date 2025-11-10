package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;

import java.util.List;

public class MovDetDaoImp extends Repository implements MovDetalheDao {

    public MovDetDaoImp(IConnection connection) {
        super(connection, MovDetalheDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public void insertEntradas(MovProdutosD mvpd) throws Exception {

    }

    @Override
    public void insertSaidas(MovProdutosD mvpd) throws Exception {

    }

    @Override
    public boolean cancelaMovimentacao(Long id) throws Exception {
        return false;
    }

    @Override
    public List<MovProdutosD> getListMovimentacoesD() throws Exception {
        return List.of();
    }
}
