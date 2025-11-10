package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;

@Getter
@Setter
public class VendedoresDto  extends BaseDto {
    private Long codigo;
    private String nome;
    private Double comissao;

    public VendedoresDto(){
        super();
    }

    public Vendedores toEntity(){
        Vendedores vendedor = new Vendedores();
        vendedor.setCodigo(this.codigo);
        vendedor.setNome(this.nome);
        vendedor.setComissao(this.comissao);
        return vendedor;
    }
}
