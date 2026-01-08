package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.ClienteDto;
import br.com.rpinfo.lorenzo.core.application.service.ClienteService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;


public class ClienteUseCase extends ClienteService {
    public ClienteUseCase(IConnection connection) {
        super(connection);
    }

    public static Response inserirCliente(ClienteDto cliente, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.adicionarCliente(cliente), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir cliente: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaClientes(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.getListClientes(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de clientes: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getCliente(Integer id, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.getClienteById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar cliente: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response atualizaCliente(ClienteDto clienteDto, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ClienteService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ClienteService(connection);
            return ResponseHandler.ok(business.atualizarCliente(clienteDto), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao atualizar clientes: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
