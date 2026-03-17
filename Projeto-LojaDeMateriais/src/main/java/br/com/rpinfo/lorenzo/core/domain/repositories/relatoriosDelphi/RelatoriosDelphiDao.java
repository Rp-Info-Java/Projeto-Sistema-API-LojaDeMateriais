package br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi;

import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RelatoriosDelphiDao {
    List<MovProdutosC> getEntradas(Date dataIni, Date dataFim) throws Exception;
    List<MovProdutosC> getSaidas(Date dataInicial, Date dataFinal) throws Exception;
    List<MovProdutosC> getCancelados(Date dataInicial, Date dataFinal) throws Exception;
}
