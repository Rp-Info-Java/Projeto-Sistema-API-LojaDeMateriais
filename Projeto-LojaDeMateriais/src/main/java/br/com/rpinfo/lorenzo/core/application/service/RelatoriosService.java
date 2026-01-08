package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.application.dto.MovProdutosCabDto;
import br.com.rpinfo.lorenzo.core.application.dto.VendedoresDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosD;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Vendedores;
import br.com.rpinfo.lorenzo.core.domain.repositories.movimentacoes.MovimentacoesDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.movimentacoes.MovimentacoesDaoImp;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatorios.RelatoriosDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatorios.RelatoriosDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;

import java.util.List;

public class RelatoriosService extends ServiceBase {

    private RelatoriosDao dao;
    private MovimentacoesDao daoMovD;

    public RelatoriosService(IConnection connection) {
        super(connection);
        this.dao = new RelatoriosDaoImp(connection);
        this.daoMovD = new MovimentacoesDaoImp(connection);
    }

    public List<MovProdutosCabDto> getEntradas() throws Exception {
        List<MovProdutosC> listMvpc = this.dao.getEntradas();
        List<MovProdutosD> listMvpd = this.daoMovD.getListMovimentacoesD();

        try {
            if (!listMvpc.isEmpty()) {
                listMvpc.forEach(mvpc -> {
                    mvpc.setItens(listMvpd.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
                });
                DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de todas as entradas nas movimentações");
                return listMvpc.stream().map(MovProdutosCabDto::new).toList();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando entradas: " + e.getMessage());
        }
    }

    public List<MovProdutosCabDto> getSaidas() throws Exception {
        List<MovProdutosC> listMvpc = this.dao.getSaidas();
        List<MovProdutosD> listMvpd = this.daoMovD.getListMovimentacoesD();

        try {
            if (!listMvpc.isEmpty()) {
                listMvpc.forEach(mvpc -> {
                    mvpc.setItens(listMvpd.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
                });
                DocumentoUtils.gravaLog(this.getConnection(), 72, "Consulta de todas as saídas nas movimentações");
                return listMvpc.stream().map(MovProdutosCabDto::new).toList();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando saídas: " + e.getMessage());
        }
    }

    public List<MovProdutosCabDto> getCanceladas() throws Exception {
        List<MovProdutosC> listMvpc = this.dao.getCanceladas();
        List<MovProdutosD> listMvpd = this.daoMovD.getListMovimentacoesD();

        try {
            if (!listMvpc.isEmpty()) {
                listMvpc.forEach(mvpc -> {
                    mvpc.setItens(listMvpd.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
                });
                DocumentoUtils.gravaLog(this.getConnection(), 73, "Consulta de movimentações canceladas");
                return listMvpc.stream().map(MovProdutosCabDto::new).toList();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando movimentações canceladas: " + e.getMessage());
        }
    }

    public List<VendedoresDto> getComissoes() throws Exception {
        List<Vendedores> vendedores = this.dao.getComissoes();

        try {
            if (!vendedores.isEmpty()) {
                DocumentoUtils.gravaLog(this.getConnection(), 74, "Consulta de comissões de cada vendedor");
                return vendedores.stream().map(VendedoresDto::new).toList();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando comissões: " + e.getMessage());
        }
    }

    public MovProdutosCabDto getEntradaTransacao(String transaction) throws Exception {
        MovProdutosC mvpc = this.dao.getMovimentacaoEntraE(transaction);
        List<MovProdutosD> mvpdList = this.daoMovD.getListMovimentacoesD();

        try {
            if (mvpc != null) {
                mvpc.setItens(mvpdList.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
                DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de entrada em movimentação específica por transação");
                return mvpc.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando entrada: " + e.getMessage());
        }
    }
}
