package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacoesDao {

    boolean insertEntradas (MovProdutosC mvpc) throws Exception;

    boolean insertSaidas (MovProdutosC mvpc) throws Exception;

    boolean cancelaMovimentacao(Long id) throws Exception;

    List<MovProdutosC> getListMovimentacoesC () throws Exception;
}
