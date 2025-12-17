package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.ConfiguracoesService;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.sql.SQLException;

public class ConfiguracoesUseCase extends ConfiguracoesService {
    public ConfiguracoesUseCase(IConnection connection) {
        super(connection);
    }

    public static Response inserirConfiguracao(ConfiguracoesDto configDto, MethodVersion methodVersion) throws ValidationException{
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            return ResponseHandler.ok(business.adicionarConfiguracao(configDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir configuração: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaConfiguracoes(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            DocumentoUtils.gravaLog(connection, 12, "Consulta de configurações");
            return ResponseHandler.ok(business.getConfiguracoes(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de configurações: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getConfiguracao(Integer id, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            DocumentoUtils.gravaLog(connection, 12, "Consulta de configurações");
            return ResponseHandler.ok(business.getConfiguracaoById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar configuração por ID: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response updateConfig(ConfiguracoesDto configDto, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ConfiguracoesService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ConfiguracoesService(connection);
            return ResponseHandler.ok(business.atualizarConfiguracoes(configDto), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao atualizar a configuração selecionada: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
