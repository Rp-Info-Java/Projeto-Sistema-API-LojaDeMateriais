package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.VendedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios.RelatoriosDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios.RelatoriosDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

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
