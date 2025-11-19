package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.ConfiguracoesService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;

public class ConfiguracoesUseCase extends ConfiguracoesService {
    public ConfiguracoesUseCase(IConnection connection) {
        super(connection);
    }

    public static Response inserirConfiguracao(ConfiguracoesDto configDto, MethodVersion methodVersion){
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            return ResponseHandler.ok(business.adicionarConfiguracao(configDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir configuração: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaConfiguracoes(MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            return ResponseHandler.ok(business.getConfiguracoes(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de configurações: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getConfiguracao(Integer id, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            return ResponseHandler.ok(business.getConfiguracaoById(id), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar configuração por ID: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
