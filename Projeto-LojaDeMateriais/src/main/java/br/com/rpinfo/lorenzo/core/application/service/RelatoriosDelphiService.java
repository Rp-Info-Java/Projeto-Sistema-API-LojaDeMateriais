package br.com.rpinfo.lorenzo.core.application.service;

import br.com.rpinfo.lorenzo.core.application.dto.*;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.*;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi.RelatoriosDelphiDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi.RelatoriosDelphiImp;
import br.com.rpinfo.lorenzo.core.domain.repositories.vendedores.VendedoresDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.vendedores.VendedoresDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;
import br.framework.interfaces.IConnection;

import java.util.Date;
import java.util.List;

public class RelatoriosDelphiService extends ServiceBase {
    private RelatoriosDelphiDao dao;

    public RelatoriosDelphiService(IConnection connection) {
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
                for (int i = 0; i < relDelphi.getRelatorioMovimentacoesEntrada().size(); i++) {
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
                for (int i = 0; i < relDelphi.getRelatorioMovimentacoesSaida().size(); i++) {
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
                for (int i = 0; i < relDelphi.getRelatorioMovimentacoesCanceladas().size(); i++) {
                    relDelphi.getRelatorioMovimentacoesCanceladas().get(i).setNomeEntidade(fornecedoresService.getFornecedorById(relDelphi.getRelatorioMovimentacoesCanceladas().get(i).getCodigoEntidade()).getNome());
                    if (relDelphi.getRelatorioMovimentacoesCanceladas().get(i).getNomeEntidade().isEmpty()) {
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

    public RelatoriosDelphiDto getGeralVendedores(String dataIni, String dataFim) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            Date dataInicio = DocumentoUtils.parseData(dataIni);
            Date dataFinal = DocumentoUtils.parseData(dataFim);

            List<VendComissoes> listVendCom = this.dao.getVendedores(dataInicio, dataFinal);

            if (!listVendCom.isEmpty()) {
                List<VendComissoesDto> listaDto = listVendCom.stream().map(VendComissoesDto::new)
                        .map(dto -> {
                            double comissao = (dto.getSoma() * dto.getVendComissao()) / 100;
                            dto.setComissao(comissao);
                            return dto;
                        })
                        .toList();

                relDelphi.setRelatorioMovimentacoesGeralVendedores(listaDto);
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro buscando relatório geral de vendedores: " + e.getMessage());
        }
    }

    public RelatoriosDelphiDto getRelatorioPendFin(String dataIni, String dataFim, String status, String pagarReceber, String tipoEnt, Integer codEnt, String pendente) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            Date dataInicio = DocumentoUtils.parseData(dataIni);
            Date dataFinal = DocumentoUtils.parseData(dataFim);

//            if (dataInicio.after(dataFinal)) {
//                throw new Exception("Data inicial não pode ser maior que a data final.");
//            }

            List<PendFin> listPendFin = this.dao.getRelPendFin(dataInicio, dataFinal, status, pagarReceber, tipoEnt, codEnt, pendente);
            if (!listPendFin.isEmpty()) {
                //DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de movimentações canceladas em Delphi");
                relDelphi.setRelatorioMovimentacoesPendFin(listPendFin.stream().map(PendFinDto::new).toList());
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro realizando a regra de negócio do relatório de pendências financeiras: " + e.getMessage());
        }
    }

    public RelatoriosDelphiDto getRelatorioProdVend(String dataIni, String dataFim) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            Date dataInicio = DocumentoUtils.parseData(dataIni);
            Date dataFinal = DocumentoUtils.parseData(dataFim);

            List<ProdVend> listProdVend = this.dao.getRelProdVend(dataInicio, dataFinal);
            if (!listProdVend.isEmpty()) {
                //DocumentoUtils.gravaLog(this.getConnection(), 71, "Consulta de movimentações canceladas em Delphi");
                relDelphi.setRelatorioMovimentacoesProdVend(listProdVend.stream().map(ProdVendDto::new).toList());
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro realizando a regra de negócio do relatório de produtos vendidos: " + e.getMessage());
        }
    }

    public RelatoriosDelphiDto getRelatorioTransacao(String transacao) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            VendedoresDao daoVend = new VendedoresDaoImp(this.getConnection());
            MovProdutosC movProdC = this.dao.getRelTransacao(transacao);

            if (movProdC != null) {
                Vendedores vend = daoVend.getVendedor(movProdC.getVend_codigo().getValue());
                MovProdutosCabDto movCDto = movProdC.toDto();

                if (movCDto.getEntradaSaida().equals("E")) {
                    movCDto.setNomeEntidade(movProdC.getFornecedores().getNome().getValue());
                } else {
                    movCDto.setNomeEntidade(movProdC.getCliente().getNome().getValue());
                }
                movCDto.setNomeVendedor(vend.getNome().getValue());

                relDelphi.getRelatorioMovimentacoesTransacao().add(movCDto);
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro realizando a regra de negócio do relatório de transação da movimentação: " + e.getMessage());
        }
    }

    public RelatoriosDelphiDto getRelatorioUnitarioVendedores(Integer codVendedor) throws Exception {
        try {
            RelatoriosDelphiDto relDelphi = new RelatoriosDelphiDto();
            MovProdutosC movProdC = this.dao.getRelUnitVendedores(codVendedor);

            if (movProdC != null) {
                MovProdutosCabDto movCDto = movProdC.toDto();

                movCDto.setNomeEntidade(movProdC.getCliente().getNome().getValue());

                relDelphi.getRelatorioMovimentacoesUnitVendedores().add(movCDto);
                return relDelphi;
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro realizando a regra de negócio do relatório unitário de vendedores: " + e.getMessage());
        }
    }
}
