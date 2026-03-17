package br.com.rpinfo.lorenzo.core.application.service;

import br.com.rpinfo.lorenzo.core.application.dto.MovProdutosCabDto;
import br.com.rpinfo.lorenzo.core.application.dto.RelatoriosDelphiDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi.RelatoriosDelphiDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi.RelatoriosDelphiImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;
import br.framework.interfaces.IConnection;

import java.util.Date;
import java.util.List;

public class RelatoriosDelphiService extends ServiceBase{
    private RelatoriosDelphiDao dao;

    public RelatoriosDelphiService(IConnection connection){
        super(connection);
        this.dao = new RelatoriosDelphiImp(connection);
    }

    public RelatoriosDelphiDto getEntradasDelphi(String dataIni, String dataFim) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            Date dataInicio = DocumentoUtils.parseData(dataIni);
            Date dataFinal = DocumentoUtils.parseData(dataFim);
            FornecedoresService fornecedoresService = new FornecedoresService(this.getConnection());

            List<MovProdutosC> listMvpc = this.dao.getEntradas(dataInicio, dataFinal);

            if (!listMvpc.isEmpty()) {
                DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de entradas nas movimentações em Delphi");
                relDelphi.setRelatorioMovimentacoesEntrada(listMvpc.stream().map(MovProdutosCabDto::new).toList());
                for (int i=0; i<relDelphi.getRelatorioMovimentacoesEntrada().size(); i++){
                    relDelphi.getRelatorioMovimentacoesEntrada().get(i).setNomeEntidade(fornecedoresService.getFornecedorById(relDelphi.getRelatorioMovimentacoesEntrada().get(i).getCodigoEntidade()).getNome());
                }
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando entradas: " + e.getMessage());
        }
    }
    public RelatoriosDelphiDto getSaidasDelphi(String dataIni, String dataFim) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            Date dataInicio = DocumentoUtils.parseData(dataIni);
            Date dataFinal = DocumentoUtils.parseData(dataFim);
            ClienteService clienteService = new ClienteService(this.getConnection());

            List<MovProdutosC> listMvpc = this.dao.getSaidas(dataInicio, dataFinal);

            if (!listMvpc.isEmpty()) {
                DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de saídas nas movimentações em Delphi");
                relDelphi.setRelatorioMovimentacoesSaida(listMvpc.stream().map(MovProdutosCabDto::new).toList());
                for (int i=0; i<relDelphi.getRelatorioMovimentacoesSaida().size(); i++){
                    relDelphi.getRelatorioMovimentacoesSaida().get(i).setNomeEntidade(clienteService.getClienteById(relDelphi.getRelatorioMovimentacoesSaida().get(i).getCodigoEntidade()).getNome());
                }
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando saídas: " + e.getMessage());
        }
    }

    public RelatoriosDelphiDto getCancelados(String dataIni, String dataFim) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            Date dataInicio = DocumentoUtils.parseData(dataIni);
            Date dataFinal = DocumentoUtils.parseData(dataFim);
            FornecedoresService fornecedoresService = new FornecedoresService(this.getConnection());
            ClienteService clienteService = new ClienteService(this.getConnection());

            List<MovProdutosC> listMvpc = this.dao.getCancelados(dataInicio, dataFinal);

            if (!listMvpc.isEmpty()) {
                DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de movimentações canceladas em Delphi");
                relDelphi.setRelatorioMovimentacoesCanceladas(listMvpc.stream().map(MovProdutosCabDto::new).toList());
                for (int i=0; i<relDelphi.getRelatorioMovimentacoesCanceladas().size(); i++){
                    relDelphi.getRelatorioMovimentacoesCanceladas().get(i).setNomeEntidade(fornecedoresService.getFornecedorById(relDelphi.getRelatorioMovimentacoesCanceladas().get(i).getCodigoEntidade()).getNome());
                    if (relDelphi.getRelatorioMovimentacoesCanceladas().get(i).getNomeEntidade().isEmpty()){
                        relDelphi.getRelatorioMovimentacoesCanceladas().get(i).setNomeEntidade(clienteService.getClienteById(relDelphi.getRelatorioMovimentacoesCanceladas().get(i).getCodigoEntidade()).getNome());
                    }
                }
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando cancelamentos: " + e.getMessage());
        }
    }
}
