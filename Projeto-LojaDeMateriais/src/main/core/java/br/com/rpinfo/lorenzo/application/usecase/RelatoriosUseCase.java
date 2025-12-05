package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.service.RelatoriosService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

public class RelatoriosUseCase extends RelatoriosService {

    public RelatoriosUseCase(IConnection connection) {
        super(connection);
    }

    public static Response getEntradas(MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getEntradas(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar entradas nas movimentações: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getSaidas(MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getSaidas(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar saidas nas movimentações: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getCanceladas(MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getCanceladas(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar movimentações canceladas: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getComissoes(MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getComissoes(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar comissoes: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getEntradaByTransaction(String transaction, MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getEntradaTransacao(transaction), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar entrada na movimentação: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
