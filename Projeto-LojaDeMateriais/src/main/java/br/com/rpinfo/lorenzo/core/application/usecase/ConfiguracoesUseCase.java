package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.ConfiguracoesDto;
import br.com.rpinfo.lorenzo.core.application.service.ConfiguracoesService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;

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
