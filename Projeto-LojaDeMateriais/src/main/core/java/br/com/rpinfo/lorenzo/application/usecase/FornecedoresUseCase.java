package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.FornecedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.FornecedoresService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

public class FornecedoresUseCase extends FornecedoresService {
    public FornecedoresUseCase(IConnection connection){
        super(connection);
    }

    public static Response inserirFornecedor(FornecedoresDto forn, MethodVersion methodVersion){
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.adicionarFornecedor(forn), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir fornecedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaFornecedores(MethodVersion methodVersion){
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.getListFornecedores(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de fornecedores: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getFornecedor(Integer id, MethodVersion methodVersion){
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.getFornecedorById(id), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de fornecedores: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response updateFornecedor(FornecedoresDto fornDto, MethodVersion methodVersion){
        IConnection connection = null;
        FornecedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new FornecedoresService(connection);
            return ResponseHandler.ok(business.atualizarFornecedor(fornDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o fornecedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
