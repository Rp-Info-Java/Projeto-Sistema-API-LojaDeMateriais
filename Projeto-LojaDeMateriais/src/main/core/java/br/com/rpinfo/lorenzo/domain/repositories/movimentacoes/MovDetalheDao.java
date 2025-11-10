package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovDetalheDao{
    void insertEntradas (MovProdutosD mvpd) throws Exception;

    void insertSaidas (MovProdutosD mvpd) throws Exception;

    boolean cancelaMovimentacao(Long id) throws Exception;

    List<MovProdutosD> getListMovimentacoesD () throws Exception;
}
