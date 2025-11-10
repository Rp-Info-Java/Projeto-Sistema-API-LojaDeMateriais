package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableAnnotation(tableName = "configuracoes", prefix = "conf_")
public class Configuracoes {
    private String nomeempresa;
    private Double percdescontos;
    private String validasaidas;
    private String validafornec;
    private String validacliente;

    public Configuracoes(){
        super();
    }
}
