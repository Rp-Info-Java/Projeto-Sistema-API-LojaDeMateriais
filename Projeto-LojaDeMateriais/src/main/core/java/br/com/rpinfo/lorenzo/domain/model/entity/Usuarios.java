package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableAnnotation(tableName = "usuarios", prefix = "usua_")
public class Usuarios {
    private Long codigo;
    private String nome;
    private String cadastros;
    private String entradas;
    private String saidas;
    private String cancel;
    private String relat;
    private String config;

    public Usuarios(){ super(); }
}
