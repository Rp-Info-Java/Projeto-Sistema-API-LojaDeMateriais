package br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi;

import br.com.rpinfo.lorenzo.core.domain.model.entity.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RelatoriosDelphiDao {
    List<MovProdutosC> getEntradas(Date dataIni, Date dataFim) throws Exception;
    List<MovProdutosC> getSaidas(Date dataInicial, Date dataFinal) throws Exception;
    List<MovProdutosC> getCancelados(Date dataInicial, Date dataFinal) throws Exception;
    List<VendComissoes> getVendedores(Date dataInicial, Date dataFinal) throws Exception;
    List<PendFin> getRelPendFin(Date dataInicial, Date dataFinal, String status, String pagarReceber, String tipoEnt, Integer codEnt, String pendente) throws Exception;
    List<ProdVend> getRelProdVend(Date dataInicial, Date dataFinal) throws Exception;
    MovProdutosC getRelTransacao(String transacao) throws Exception;
    MovProdutosC getRelUnitVendedores(Integer codVendedor) throws Exception;
    List<ProdutosMovimentacoes> getRelProdComprados(Date dataInicial, Date dataFinal) throws Exception;
    List<PendFin> getRelDocBx(Date dataInicial, Date dataFinal) throws Exception;
    //List<Vendedores> getVendedoresMovimentacoes(String transacao) throws Exception;
}
