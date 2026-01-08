package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.service.RelatoriosService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

public class RelatoriosUseCase extends RelatoriosService {

    public RelatoriosUseCase(IConnection connection) {
        super(connection);
    }

    public static Response getEntradas(MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getEntradas(), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar entradas nas movimentações: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getSaidas(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getSaidas(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar saidas nas movimentações: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getCanceladas(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getCanceladas(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar movimentações canceladas: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getComissoes(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getComissoes(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar comissoes: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Response getEntradaByTransaction(String transaction, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        RelatoriosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new RelatoriosService(connection);
            return ResponseHandler.ok(business.getEntradaTransacao(transaction), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar entrada na movimentação: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
