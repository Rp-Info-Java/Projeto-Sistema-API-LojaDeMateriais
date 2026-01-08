package br.com.rpinfo.lorenzo.core.domain.repositories.movimentacoes;

import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosD;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacoesDao {

    boolean insertEntradas (MovProdutosC mvpc) throws Exception;

    boolean insertSaidas (MovProdutosC mvpc, String transacao) throws Exception;

    boolean cancelaMovimentacao(MovProdutosC mvpc) throws Exception;

    MovProdutosC getMovimentacaoC(String transaction) throws Exception;

    List<MovProdutosD> getMovimentacaoD(String transaction) throws Exception;

    List<MovProdutosC> getListMovimentacoesC () throws Exception;

    List<MovProdutosD> getListMovimentacoesD() throws Exception;
}
