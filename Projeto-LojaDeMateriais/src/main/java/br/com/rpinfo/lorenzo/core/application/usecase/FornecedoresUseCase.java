package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.FornecedoresDto;
import br.com.rpinfo.lorenzo.core.application.service.FornecedoresService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

public class FornecedoresUseCase extends FornecedoresService {
    public FornecedoresUseCase(IConnection connection){
        super(connection);
    }

    public static Response inserirFornecedor(FornecedoresDto forn, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.adicionarFornecedor(forn), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir fornecedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaFornecedores(MethodVersion methodVersion) throws NullPointerException{
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.getListFornecedores(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de fornecedores: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getFornecedor(Integer id, MethodVersion methodVersion) throws NullPointerException{
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.getFornecedorById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de fornecedores: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response updateFornecedor(FornecedoresDto fornDto, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.atualizarFornecedor(fornDto), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao atualizar o fornecedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
