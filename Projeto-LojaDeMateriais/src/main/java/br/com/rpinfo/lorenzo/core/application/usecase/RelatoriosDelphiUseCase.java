package br.com.rpinfo.lorenzo.core.application.usecase;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.service.RelatoriosDelphiService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;
import br.framework.interfaces.IConnection;

public class RelatoriosDelphiUseCase extends RelatoriosDelphiService {
    public RelatoriosDelphiUseCase(IConnection connection) { super(connection); }

    public static Response getEntradasMovimentacoes(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getEntradasDelphi(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar entradas nas movimentações: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getSaidasMovimentacoes(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getSaidasDelphi(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar saídas nas movimentações: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getMovimentacoesCanceladas(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getCancelados(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar movimentações canceladas: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getMovimentacoesGeralVendedores(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getGeralVendedores(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório geral de vendedores: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getPendenciasFinanceiras(String dataIni, String dataFim, String status, String pagarReceber, String tipoEnt, Integer codEnt, String pendente, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getRelatorioPendFin(dataIni, dataFim, status, pagarReceber, tipoEnt, codEnt, pendente), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório de pendências financeiras: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getProdutosVendidos(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getRelatorioProdVend(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório de produtos vendidos: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getMovTransacao(String transacao, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getRelatorioTransacao(transacao), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório de transação: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getRelUnitVend(Integer codVendedor, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getRelatorioUnitarioVendedores(codVendedor), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório unitário de vendedores: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getRelProdComprados(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getRelatorioProdutosComprados(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório de produtos comprados: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public static Response getRelDocumentosBx(String dataIni, String dataFim, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosDelphiService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosDelphiService(connection);
            return ResponseHandler.ok(business.getRelatorioDocBaixados(dataIni, dataFim), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar relatório de documentos baixados: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
