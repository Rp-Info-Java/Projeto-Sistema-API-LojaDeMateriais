package br.com.rpinfo.lorenzo.core.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.application.dto.LogOperacoesDto;
import br.com.rpinfo.lorenzo.core.domain.model.field.Data;
import br.com.rpinfo.lorenzo.core.domain.model.field.Descricao;
import br.com.rpinfo.lorenzo.core.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "logoperacoes", prefix = "log_")
public class LogOperacoes extends EntityClass implements Serializable {

    private Numerico codigo = new Numerico(true);
    private Data data = new Data(true);
    private Descricao hora = new Descricao(true);
    private Numerico usua_codigo = new Numerico(true);
    private Descricao descricao = new Descricao(true);
    private Descricao agrupamento = new Descricao(true);

    public LogOperacoes() {
        super();
    }

    public LogOperacoes(Boolean autoEnableFields){
        super();
    }

    public LogOperacoesDto toDto(){
        LogOperacoesDto logDto = new LogOperacoesDto();
        logDto.setCodigo(this.getCodigo().getValue());
        logDto.setData(this.getData().getValue());
        logDto.setHora(this.getHora().getValue());
        logDto.setCodigoUsuario(this.getUsua_codigo().getValue());
        logDto.setDescricao(this.getDescricao().getValue());
        logDto.setAgrupamento(this.getAgrupamento().getValue());
        return logDto;
    }
}
