package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import br.com.rpinfo.lorenzo.core.application.dto.ConfiguracoesDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Configuracoes;
import br.com.rpinfo.lorenzo.core.domain.repositories.configuracoes.ConfiguracoesDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.configuracoes.ConfiguracoesDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;

import java.sql.SQLException;
import java.util.List;

public class ConfiguracoesService extends ServiceBase {
    private ConfiguracoesDao dao;

    public ConfiguracoesService(IConnection connection) {
        super(connection);
        this.dao = new ConfiguracoesDaoImp(connection);
    }

    public boolean adicionarConfiguracao(ConfiguracoesDto configDto) throws ValidationException {
        try {
            if (configDto == null) {
                throw new ValidationException("Os dados da configuração são nulos.");
            }
            Configuracoes config = configDto.toEntity();
            if (DocumentoUtils.validarCamposConfig(config.getValidasaidas().getValue()) && DocumentoUtils.validarCamposConfig(config.getValidafornec().getValue()) && DocumentoUtils.validarCamposConfig(config.getValidacliente().getValue())) {
                if (this.dao.insertConfiguracao(config)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 10, "Gravação de uma nova configuração no banco de dados");
                    return true;
                }
            }
            return false;
        } catch (ValidationException e) {
            throw new ValidationException("Erro ao adicionar configuração: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ConfiguracoesDto> getConfiguracoes() throws Exception {
        List<Configuracoes> listConfiguracoes = this.dao.getListConfiguracoes();

        if (!listConfiguracoes.isEmpty()) {
            DocumentoUtils.gravaLog(this.getConnection(), 12, "Consulta da lista de configurações disponíveis");
            return listConfiguracoes.stream().map(ConfiguracoesDto::new).toList();
        }
        return null;
    }

    public ConfiguracoesDto getConfiguracaoById(Integer id) throws Exception {
        Configuracoes config = this.dao.getConfiguracao(id);
        try {
            if (config != null) {
                DocumentoUtils.gravaLog(this.getConnection(), 12, "Consulta de uma configuração específica por ID");
                return config.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar configuração por ID: " + e.getMessage());
        }
    }

    public boolean atualizarConfiguracoes(ConfiguracoesDto configDto) throws Exception {
        Configuracoes config = this.dao.getConfiguracao(configDto.getCodigo());
        try {
            if (config != null) {
                if (!Strings.isNullOrEmpty(configDto.getNomeEmpresa())) {
                    config.getNomeempresa().setValue(configDto.getNomeEmpresa());
                }
                if (!Strings.isNullOrEmpty(configDto.getValidaSaidas())) {
                    if (DocumentoUtils.validarCamposConfig(config.getValidasaidas().getValue())) {
                        config.getValidasaidas().setValue(configDto.getValidaSaidas());
                    }
                }
                if (!Strings.isNullOrEmpty(configDto.getValidaFornecedor())) {
                    if (DocumentoUtils.validarCamposConfig(config.getValidafornec().getValue())) {
                        config.getValidafornec().setValue(configDto.getValidaFornecedor());
                    }
                }
                if (!Strings.isNullOrEmpty(configDto.getValidaCliente())) {
                    if (DocumentoUtils.validarCamposConfig(config.getValidacliente().getValue())) {
                        config.getValidacliente().setValue(configDto.getValidaCliente());
                    }
                }
                if (configDto.getPercentualDescontos() != null) {
                    config.getPercdescontos().setValue(configDto.getPercentualDescontos());
                }
                if (this.dao.updateConfiguracoes(config)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 11, "Edição de campo(s) em uma configuração específica");
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar configuração: " + e.getMessage());
        }
    }
}
