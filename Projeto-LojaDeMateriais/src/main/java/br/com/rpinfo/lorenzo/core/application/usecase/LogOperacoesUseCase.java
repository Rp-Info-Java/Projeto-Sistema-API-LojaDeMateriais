package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.service.LogOperacoesService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

public class LogOperacoesUseCase extends LogOperacoesService {
    public LogOperacoesUseCase(IConnection connection) {
        super(connection);
    }

    public static Response getListaLogOperacoes(MethodVersion methodVersion, Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim, Integer codigoOperacao) throws NullPointerException {
        IConnection connection = null;
        LogOperacoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new LogOperacoesService(connection);
            return ResponseHandler.ok(business.getListLogOperacoes(codigoDeUsuario, tipoOperacao, dataInicio, dataFim, codigoOperacao), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de log de operações: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

}
