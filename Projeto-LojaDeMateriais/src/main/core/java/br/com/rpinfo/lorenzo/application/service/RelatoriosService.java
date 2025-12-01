package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.VendedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios.RelatoriosDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios.RelatoriosDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class RelatoriosService extends ServiceBase{

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

        if(!listMvpc.isEmpty()){
            listMvpc.forEach(mvpc -> {
                mvpc.setItens(listMvpd.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
            });
            DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de entradas nas movimentações");
            return listMvpc.stream().map(MovProdutosCabDto::new).toList();
        }
        return null;
    }

    public List<MovProdutosCabDto> getSaidas() throws Exception {
        List<MovProdutosC> listMvpc = this.dao.getSaidas();
        List<MovProdutosD> listMvpd = this.daoMovD.getListMovimentacoesD();

        if(!listMvpc.isEmpty()) {
            listMvpc.forEach(mvpc -> {
                mvpc.setItens(listMvpd.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
            });
            DocumentoUtils.gravaLog(this.getConnection(), 72, "Consulta de saídas nas movimentações");
            return listMvpc.stream().map(MovProdutosCabDto::new).toList();
        }
        return null;
    }

    public List<MovProdutosCabDto> getCanceladas() throws Exception {
        List<MovProdutosC> listMvpc = this.dao.getCanceladas();
        List<MovProdutosD> listMvpd = this.daoMovD.getListMovimentacoesD();

        if(!listMvpc.isEmpty()) {
            listMvpc.forEach(mvpc -> {
                mvpc.setItens(listMvpd.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
            });
            DocumentoUtils.gravaLog(this.getConnection(), 73, "Consulta de movimentações canceladas");
            return listMvpc.stream().map(MovProdutosCabDto::new).toList();
        }
        return null;
    }

    public List<VendedoresDto> getComissoes() throws Exception {
        List<Vendedores> vendedores = this.dao.getComissoes();

        if(!vendedores.isEmpty()){
            DocumentoUtils.gravaLog(this.getConnection(), 74, "Consulta de comissões dos vendedores");
            return vendedores.stream().map(VendedoresDto::new).toList();
        }
        return null;
    }
}
