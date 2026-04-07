package br.com.rpinfo.lorenzo.core.application.usecase;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.service.FormasPagamentoService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;
import br.framework.interfaces.IConnection;

public class FormasPagamentoUseCase extends FormasPagamentoService {
    public FormasPagamentoUseCase(IConnection connection) { super(connection); }

    public static Response getFpgtoById(Integer id, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        FormasPagamentoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FormasPagamentoService(connection);
            return ResponseHandler.ok(business.getFormasPagamentoById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar forma de pagamento: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
