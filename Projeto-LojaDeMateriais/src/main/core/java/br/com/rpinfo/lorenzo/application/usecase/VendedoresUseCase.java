package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.VendedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.ClienteService;
import main.core.java.br.com.rpinfo.lorenzo.application.service.VendedoresService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;

public class VendedoresUseCase extends VendedoresService {
    public VendedoresUseCase(IConnection connection) { super(connection);}

    public static Response inserirVendedor(VendedoresDto vendDto, MethodVersion methodVersion){
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.adicionarVendedor(vendDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir vendedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaVendedores(MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.getListVendedores(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de vendedores: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getVendedorById(Integer id, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.getVendedorById(id), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar vendedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response atualizaVendedor(VendedoresDto vendDto, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        VendedoresService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new VendedoresService(connection);
            return ResponseHandler.ok(business.atualizarVendedor(vendDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar vendedor: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
