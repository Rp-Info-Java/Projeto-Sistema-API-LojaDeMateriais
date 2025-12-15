package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosDetDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.*;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores.FornecedoresDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores.FornecedoresDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores.VendedoresDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores.VendedoresDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovProdutoService extends ServiceBase {

    private MovimentacoesDao dao;
    private FornecedoresDao daoForn;
    private VendedoresDao daoVen;
    private ProdutoDao daoProd;

    public MovProdutoService(IConnection connection) {
        super(connection);
        this.dao = new MovimentacoesDaoImp(connection);
        this.daoForn = new FornecedoresDaoImp(connection);
        this.daoVen = new VendedoresDaoImp(connection);
        this.daoProd = new ProdutoDaoImp(connection);
    }

    public boolean adicionarEntradas(MovProdutosCabDto mvpcDto, ConfiguracoesDto config) throws Exception {
        Fornecedores forn = this.daoForn.getFornecedor(mvpcDto.getCodigoEntidade());
        Vendedores vend = this.daoVen.getVendedor(mvpcDto.getCodigoVendedor());
        try {
            if (mvpcDto == null) {
                throw new Exception("Os dados da movimentação são nulos.");
            }

            MovProdutosC mvpc = mvpcDto.toEntity();

            if (forn != null) {
                if (DocumentoUtils.validarImpedimento(config, forn.getSituacao().getValue())) {
                    mvpc.getCodentidade().setValue(forn.getCodigo().getValue());
                    mvpc.getTipoentidade().setValue(forn.getTipo().getValue());
                } else {
                    return false; //Garante que, caso o fornecedor esteja impedido, a movimentação não seja inserida.
                }
                if (vend != null) {
                    mvpc.getVend_codigo().setValue(vend.getCodigo().getValue());
                } else {
                    return false; //Garante que, caso o vendedor seja nulo, a movimentação não seja inserida.
                }

                List<MovProdutosD> listD = mvpc.getItens();

                for (int i = 0; i < listD.size(); i++) {
                    listD.get(i).getStatus().setValue("N");
                }

                mvpc.setItens(listD);
                mvpc.getStatus().setValue("N");             //N ou C, sempre seta "N"
                mvpc.getEs().setValue("E");                 //E ou S, sempre seta "E" em adicionarEntradas

                mvpc = this.validarConfiguracao(mvpc, config);

                if (this.atualizarEstoque(mvpc.getItens(), mvpc.getEs().getValue())) {
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
        MovProdutosC mvpc = this.dao.getMovimentacaoC(mvpcDto.getTransacao());
        Fornecedores forn = this.daoForn.getFornecedor(mvpcDto.getCodigoEntidade());
        Vendedores vend = this.daoVen.getVendedor(mvpcDto.getCodigoVendedor());

        if (forn == null) {
            throw new Exception("Os dados do fornecedor são nulos.");
        }
        if (vend == null) {
            throw new Exception("Os dados do vendedor são nulos.");
        }

        try {
            if (DocumentoUtils.validarStatusMovimento(config, mvpc.getStatus().getValue()) && DocumentoUtils.validarImpedimento(config, forn.getSituacao().getValue())) {
                if (mvpc != null) {
                    if (!Strings.isNullOrEmpty(mvpcDto.getNumeroDocumento())) {
                        mvpc.getNumdcto().setValue(mvpcDto.getNumeroDocumento());
                    }
                    if (!Strings.isNullOrEmpty(mvpcDto.getDataMovimento())) {
                        mvpc.getDatamvto().setValue(mvpcDto.getDataMovimento());
                    }
                    if (!Strings.isNullOrEmpty(mvpcDto.getStatus())) {
                        if (this.validarStatus(mvpcDto.getStatus())) {
                            mvpc.getStatus().setValue(mvpcDto.getStatus());
                        }
                    }
                    if (!Strings.isNullOrEmpty(forn.getTipo().getValue())) {
                        mvpc.getTipoentidade().setValue(forn.getTipo().getValue());
                    }
                    if (forn.getCodigo().getValue() != null) {
                        mvpc.getCodentidade().setValue(forn.getCodigo().getValue());
                    }
                    if (vend.getCodigo().getValue() != null) {
                        mvpc.getVend_codigo().setValue(vend.getCodigo().getValue());
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
                    if (mvpcDto.getItens() != null) {
                        List<MovProdutosD> itensMov = this.dao.getMovimentacaoD(mvpc.getTransacao().getValue());
                        mvpc.setItens(itensMov);
                        List<MovProdutosD> itens = new ArrayList<>();

                        for (int i = 0; i < itensMov.size(); i++) {
                            MovProdutosD item = itensMov.get(i);
                            MovProdutosDetDto dto = mvpcDto.getItens().stream().filter(d -> Objects.equals(d.getCodigoProduto(), item.getProd_codigo().getValue())).findFirst().orElse(null);

                            if (dto != null) {
                                Integer codigoProdutoOriginal = item.getProd_codigo().getValue();

                                if (dto.getQuantidade() != null) {
                                    item.getQtde().setValue(dto.getQuantidade());
                                }
                                if (dto.getValorDesc() != null){
                                    item.getValordesc().setValue(dto.getValorDesc());
                                }
                                if (dto.getValorAcrescimo() != null) {
                                    item.getValoracres().setValue(dto.getValorAcrescimo());
                                }
                                if (dto.getValorOutros() != null) {
                                    item.getValoroutros().setValue(dto.getValorOutros());
                                }
                                if (dto.getValorTotal() != null) {
                                    item.getValortotal().setValue(dto.getValorTotal());
                                }
                                item.getStatus().setValue("N");
                                item.toUpdate("mvpd_transacao = '" + mvpc.getTransacao().getValue() +
                                        "' AND mvpd_prod_codigo = " + codigoProdutoOriginal);
                                itens.add(item);
                            }

                        }
                        mvpc.setItens(itens);
                    }
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
                if (!listD.isEmpty()) {
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
}
