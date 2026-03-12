package br.com.rpinfo.lorenzo.core.domain.repositories.movimentacoes;

import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosD;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface MovimentacoesDao {
    String insertEntradaRetornaTransacao (MovProdutosC mvpc) throws Exception;

    Boolean insertEntradas (MovProdutosC mvpc) throws Exception;

    boolean insertSaidas (MovProdutosC mvpc, String transacao) throws Exception;

    boolean cancelaMovimentacao(MovProdutosC mvpc) throws Exception;

    MovProdutosC getMovimentacaoC(String transaction) throws Exception;

    List<MovProdutosD> getMovimentacaoD(String transaction) throws Exception;

    List<MovProdutosC> getListMovimentacoesC () throws Exception;

    List<MovProdutosD> getListMovimentacoesD() throws Exception;

    List<MovProdutosC> getAllMovimentacoes(String condES, String es, LocalDate dataInicial, LocalDate dataFinal, String condStatus) throws Exception;
}
