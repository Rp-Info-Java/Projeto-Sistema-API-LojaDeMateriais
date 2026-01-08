package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.VendedoresDto;
import br.com.rpinfo.lorenzo.core.application.service.ClienteService;
import br.com.rpinfo.lorenzo.core.application.service.VendedoresService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;

public class VendedoresUseCase extends VendedoresService {
    public VendedoresUseCase(IConnection connection) { super(connection);}

    public static Response inserirVendedor(VendedoresDto vendDto, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.adicionarVendedor(vendDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir vendedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaVendedores(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.getListVendedores(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de vendedores: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getVendedorById(Integer id, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.getVendedorById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar vendedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response atualizaVendedor(VendedoresDto vendDto, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.atualizarVendedor(vendDto), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao atualizar vendedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
