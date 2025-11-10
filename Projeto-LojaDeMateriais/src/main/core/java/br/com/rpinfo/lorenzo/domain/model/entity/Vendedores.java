package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableAnnotation(tableName = "vendedores", prefix = "vend_")
public class Vendedores {
    private Long codigo;
    private String nome;
    private Double comissao;

    public Vendedores(){ super(); }
}
