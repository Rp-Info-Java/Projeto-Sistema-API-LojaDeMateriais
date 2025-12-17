package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.service.LogOperacoesService;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

public class LogOperacoesUseCase extends LogOperacoesService {
    public LogOperacoesUseCase(IConnection connection) {
        super(connection);
    }

    public static Response getListaLogOperacoes(MethodVersion methodVersion, Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim) throws NullPointerException {
        IConnection connection = null;
        LogOperacoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new LogOperacoesService(connection);
            return ResponseHandler.ok(business.getListLogOperacoes(codigoDeUsuario, tipoOperacao, dataInicio, dataFim), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de log de operações: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

}
