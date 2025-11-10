package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;

import java.util.List;

public class MovCDaoImp extends Repository implements MovCabecalhoDao {

    public MovCDaoImp(IConnection connection) {
        super(connection, MovCabecalhoDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public void insertEntradas(MovProdutosC mvpc) throws Exception {

    }

    @Override
    public void insertSaidas(MovProdutosC mvpc) throws Exception {

    }

    @Override
    public boolean cancelaMovimentacao(Long id) throws Exception {
        return false;
    }

    @Override
    public List<MovProdutosC> getListMovimentacoesC() throws Exception {
        return List.of();
    }
}
