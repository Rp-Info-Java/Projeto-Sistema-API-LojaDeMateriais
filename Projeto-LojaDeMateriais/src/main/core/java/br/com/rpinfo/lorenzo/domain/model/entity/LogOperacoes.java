package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Data;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

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

    public LogOperacoes() {
        super();
    }

    public LogOperacoes(Boolean autoEnableFields){
        super();
    }
}
