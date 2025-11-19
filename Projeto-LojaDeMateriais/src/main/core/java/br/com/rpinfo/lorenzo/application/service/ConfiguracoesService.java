package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Configuracoes;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.configuracoes.ConfiguracoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.configuracoes.ConfiguracoesDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class ConfiguracoesService extends ServiceBase{
    private ConfiguracoesDao dao;

    public ConfiguracoesService(IConnection connection){
        super(connection);
        this.dao = new ConfiguracoesDaoImp(connection);
    }

    public boolean adicionarConfiguracao(ConfiguracoesDto configDto) throws ValidationException{
        try{
            if(configDto == null){
                throw new ValidationException("Os dados da configuração são nulos.");
            }
            Configuracoes config = configDto.toEntity();
            if(DocumentoUtils.validarCamposConfig(config.getValidasaidas().getValue()) && DocumentoUtils.validarCamposConfig(config.getValidafornec().getValue()) && DocumentoUtils.validarCamposConfig(config.getValidacliente().getValue())){
                if(this.dao.insertConfiguracao(config)){
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new ValidationException("Erro ao adicionar configuração: " + e.getMessage());
        }
    }

    public List<ConfiguracoesDto> getConfiguracoes() throws Exception{
        List<Configuracoes> listConfiguracoes = this.dao.getListConfiguracoes();

        if(!listConfiguracoes.isEmpty()){
            return listConfiguracoes.stream().map(ConfiguracoesDto::new).toList();
        }
        return null;
    }

    public ConfiguracoesDto getConfiguracaoById(Integer id) throws Exception{
        Configuracoes config = this.dao.getConfiguracao(id);
        try{
            if(config != null){
                return config.toDto();
            }
            return null;
        }catch (Exception e){
            throw new ValidationException("Erro ao buscar configuração por ID: " + e.getMessage());
        }
    }
}
