package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.application.dto.ConfiguracoesDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Decimal;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "configuracoes", prefix = "conf_")
public class Configuracoes extends EntityClass implements Serializable {

    private Numerico codigo = new Numerico(true);
    private Descricao nomeempresa = new Descricao(true);
    private Decimal percdescontos = new Decimal(true);
    private Descricao validasaidas = new Descricao(true);
    private Descricao validafornec = new Descricao(true);
    private Descricao validacliente = new Descricao(true);

    public Configuracoes(){
        super();
    }

    public Configuracoes(Boolean autoEnableFields) { super(); }

    public ConfiguracoesDto toDto(){
        ConfiguracoesDto configDto = new ConfiguracoesDto();
        configDto.setCodigo(this.getCodigo().getValue());
        configDto.setNomeEmpresa(this.getNomeempresa().getValue());
        configDto.setPercentualDescontos(this.getPercdescontos().getValue());
        configDto.setValidaSaidas(this.getValidasaidas().getValue());
        configDto.setValidaFornecedor(this.getValidafornec().getValue());
        configDto.setValidaCliente(this.getValidacliente().getValue());
        return configDto;
    }
}
