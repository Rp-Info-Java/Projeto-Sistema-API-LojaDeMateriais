package br.com.rpinfo.lorenzo.core.application.usecase;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.PagamentoDto;
import br.com.rpinfo.lorenzo.core.application.service.PendFinService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;
import br.framework.interfaces.IConnection;

public class PendFinUseCase extends PendFinService {
    public PendFinUseCase(IConnection connection) { super(connection); }

    public static Response inserirPendFin(PagamentoDto pgtoDto, MethodVersion methodVersion) throws ValidationException{
        IConnection connection = null;
        PendFinService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new PendFinService(connection);
            return ResponseHandler.ok(business.adicionarPendFin(pgtoDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir a pendência financeira: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
