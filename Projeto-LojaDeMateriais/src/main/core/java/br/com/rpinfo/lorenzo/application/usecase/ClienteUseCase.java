package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ClienteDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.ClienteService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;


public class ClienteUseCase extends ClienteService {
    public ClienteUseCase(IConnection connection) {
        super(connection);
    }

    public static Response inserirCliente(ClienteDto cliente, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.adicionarCliente(cliente), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaClientes(MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.getListClientes(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de clientes: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getCliente(Integer id, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.getClienteById(id), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response atualizaCliente(ClienteDto clienteDto, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.atualizarCliente(clienteDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar clientes: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
