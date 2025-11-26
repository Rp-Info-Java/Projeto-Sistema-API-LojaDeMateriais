package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class MovProdutoService extends ServiceBase {

    private MovimentacoesDao dao;

    public MovProdutoService(IConnection connection) {
        super(connection);
        this.dao = new MovimentacoesDaoImp(connection);
    }

    public boolean adicionarEntradas(MovProdutosCabDto mvpcDto, ConfiguracoesDto config) throws Exception {
        try {
            if (mvpcDto == null) {
                throw new Exception("Os dados da movimentação são nulos.");
            }
            MovProdutosC mvpc = mvpcDto.toEntity();
            List<MovProdutosD> listD = mvpc.getItens();

            if (this.validarTipoEntidade(mvpc)) {
                for (int i = 0; i < listD.size(); i++) {
                    listD.get(i).getStatus().setValue("N");
                }
                mvpc.setItens(listD);
                mvpc.getStatus().setValue("N");             //N ou C, sempre seta "N"
                mvpc.getEs().setValue("E");                 //E ou S, sempre seta "E" em adicionarEntradas

                mvpc = this.validarConfiguracao(mvpc, config);

                if(this.atualizarEstoque(mvpc.getItens(), mvpc.getEs().getValue())){
                    DocumentoUtils.gravaLog(this.getConnection(), 50, "Gravação de movimentação");
                    return this.dao.insertEntradas(mvpc);
                }
            }

            return false;
        } catch (Exception e) {
            throw new Exception("Erro ao inserir entradas na movimentação: " + e.getMessage());
        }
    }

    public boolean adicionarSaidas(MovProdutosCabDto mvpcDto, ConfiguracoesDto config) throws Exception {
        MovProdutosC mvpc = mvpcDto.toEntity();
        int i = 0;

        try {
            if (validarSaida(config)) {
                if (mvpc != null) {
                    if (!Strings.isNullOrEmpty(mvpcDto.getNumeroDocumento())) {
                        mvpc.getNumdcto().setValue(mvpcDto.getNumeroDocumento());
                    }
                    if (!Strings.isNullOrEmpty(mvpcDto.getDataMovimento())) {
                        mvpc.getDatamvto().setValue(mvpcDto.getDataMovimento());
                    }
                    if (!Strings.isNullOrEmpty(mvpcDto.getStatus())) {
                        if (this.validarStatus(mvpc.getStatus().getValue())) {
                            mvpc.getStatus().setValue(mvpcDto.getStatus());
                        } else {
                            return false;
                        }
                    }
                    if (!Strings.isNullOrEmpty(mvpcDto.getTipoEntidade())) {
                        if (this.validarTipoEntidade(mvpc)) {
                            mvpc.getTipoentidade().setValue(mvpcDto.getTipoEntidade());
                        } else {
                            return false;
                        }
                    }
                    if (mvpcDto.getCodigoEntidade() != null) {
                        mvpc.getCodentidade().setValue(mvpcDto.getCodigoEntidade());
                    }
                    if (mvpcDto.getCodigoVendedor() != null) {
                        mvpc.getVend_codigo().setValue(mvpcDto.getCodigoVendedor());
                    }
                    if (mvpcDto.getTotalProdutos() != null) {
                        mvpc.getTotalprod().setValue(mvpcDto.getTotalProdutos());
                    }
                    if (mvpcDto.getTotalAcrescimo() != null) {
                        mvpc.getTotalacres().setValue(mvpcDto.getTotalAcrescimo());
                    }
                    if (mvpcDto.getTotalOutros() != null) {
                        mvpc.getTotaloutros().setValue(mvpcDto.getTotalOutros());
                    }
                    if (mvpcDto.getTotalDocumento() != null) {
                        mvpc.getTotaldcto().setValue(mvpcDto.getTotalDocumento());
                    }

//                for (MovProdutosD item : listaMovD) {
//                    MovProdutosDetDto produto2 = mvpcDto.getItens().stream().filter(p -> p.getCodigoProduto().equals(item.getProd_codigo().getValue())).findFirst().orElse(null);
//
//                    if(produto2 != null){
//                        item.getQtde().setValue(produto2.getQuantidade());
//                        item.getValordesc().setValue(produto2.getValorDesc());
//                        item.getValoracres().setValue(produto2.getValorAcrescimo());
//                        item.getValoroutros().setValue(produto2.getValorOutros());
//                        item.getValortotal().setValue(produto2.getValorTotal());
//                    }
//                    if (!this.validarStatus(item.getStatus().getValue())) {
//                        return false;
//                    }
//                    if (item.getProd_codigo() != null && item.getQtde() != null && item.getValordesc() != null
//                            && item.getValoracres() != null && item.getValoroutros() != null && item.getValortotal() != null) {
//                        item.toUpdate("mvpd_transacao = '" + mvpc.getTransacao() + "'");
//                        listaMovD.set(i, item);
//                    }
//                    i++;
//                }
                    mvpc.getEs().setValue("S");                 //sempre seta "S" em adicionarSaidas

                    mvpc = this.validarConfiguracao(mvpc, config);

                    DocumentoUtils.gravaLog(this.getConnection(), 51, "Edição de movimentação (Saídas)");

                    this.atualizarEstoque(mvpc.getItens(), mvpc.getEs().getValue());
                    return this.dao.insertSaidas(mvpc, mvpc.getTransacao().getValue());
                }
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir saidas na movimentação: " + e.getMessage());
        }
        return false;
    }

    public boolean cancelarMovimentacao(String transacao) throws Exception {
        try {
            MovProdutosC mvpc = this.dao.getMovimentacaoC(transacao);
            List<MovProdutosD> listD = this.dao.getMovimentacaoD(transacao);
            int i = 0;

            if (mvpc != null) {
                mvpc.getStatus().setValue("C");
                for (MovProdutosD item : listD) {
                    item.getStatus().setValue("C");
                    item.toUpdate("mvpd_transacao = '" + mvpc.getTransacao().getValue() + "'");
                    listD.set(i, item);
                    i++;
                }
                mvpc.setItens(listD);

                DocumentoUtils.gravaLog(this.getConnection(), 51, "Cancelamento de movimentação");

                return this.dao.cancelaMovimentacao(mvpc);
            }
            return false;
        } catch (Exception e) {
            throw new Exception("Erro ao cancelar movimentação: " + e.getMessage());
        }
    }

    public List<MovProdutosCabDto> getListMovimentacoesCD() throws Exception {
        List<MovProdutosC> list = this.dao.getListMovimentacoesC();
        List<MovProdutosD> listMovD = this.dao.getListMovimentacoesD();

        if (!list.isEmpty()) {
            if (!listMovD.isEmpty()) {
                list.forEach(mvpc -> {
                    mvpc.setItens(listMovD.stream().filter(mvpd -> mvpd.getTransacao().getValue().equals(mvpc.getTransacao().getValue())).toList());
                });
            }

            DocumentoUtils.gravaLog(this.getConnection(), 52, "Consulta de movimentações");
            return list.stream().map(MovProdutosCabDto::new).toList();
        }
        return null;
    }

    public MovProdutosCabDto getMovimentacaoByTransaction(String transaction) throws Exception {
        try {
            MovProdutosC mvpc = this.dao.getMovimentacaoC(transaction);
            List<MovProdutosD> listD = this.dao.getMovimentacaoD(transaction);

            if (mvpc != null) {
                if(!listD.isEmpty()){
                    mvpc.setItens(listD);
                }
                DocumentoUtils.gravaLog(this.getConnection(), 52, "Consulta de movimentação por transação");
                return mvpc.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Erro ao cancelar movimentação: " + e.getMessage());
        }
    }

    public boolean atualizarEstoque(List<MovProdutosD> listaProdutos, String entSaida) throws Exception {
        ProdutosService prodServ = new ProdutosService(getConnection());

        if (listaProdutos.isEmpty()) {
            return false;
        }
        for (MovProdutosD mvpd : listaProdutos) {
            Double qtd = mvpd.getQtde().getValue();

            if ("S".equalsIgnoreCase(entSaida)) {
                qtd = qtd * (-1);
            }

            try {
                prodServ.atualizarEstoque(mvpd.getProd_codigo().getValue(), qtd);
            } catch (Exception e) {
                throw new Exception("Falha na atualização do estoque para o produto: " + mvpd.getProd_codigo(), e);
            }
        }
        return true;
    }

    public boolean validarStatus(String status) {
        if (status != null) {
            return status.equals("N");
        }
        return false;
    }

    public boolean validarTipoEntidade(MovProdutosC mvpc) {
        if (mvpc.getTipoentidade().getValue() != null) {
            return mvpc.getTipoentidade().getValue().equals("F") || mvpc.getTipoentidade().getValue().equals("J");
        }
        return false;
    }

    public MovProdutosC validarConfiguracao(MovProdutosC mvpc, ConfiguracoesDto config) throws Exception {
        if (mvpc != null && config != null) {
            if (config.getPercentualDescontos() > 0) {
                if (Integer.parseInt(mvpc.getNumdcto().getValue()) == config.getCodigo()) {
                    mvpc.getTotaldesc().setValue((config.getPercentualDescontos() * mvpc.getTotalprod().getValue()) / 100);
                    mvpc.getTotalprod().setValue(mvpc.getTotalprod().getValue() - mvpc.getTotaldesc().getValue());
                }
            } else {
                mvpc.getTotaldesc().setValue(0D);
            }
        }
        return mvpc;
    }

    public boolean validarSaida(ConfiguracoesDto config) {
        if (config != null) {
            return ("S").equals(config.getValidaSaidas());
        }
        return false;
    }
}
