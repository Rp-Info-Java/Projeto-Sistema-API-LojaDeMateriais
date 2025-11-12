package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;

@Getter
@Setter
public class VendedoresDto  extends BaseDto {
    private Integer codigo;
    private String nome;
    private Double comissao;

    public VendedoresDto(){
        super();
    }

    public VendedoresDto(Vendedores vendedor){
        this.codigo = vendedor.getCodigo().getValue();
        this.nome = vendedor.getNome().getValue();
        this.comissao = vendedor.getComissao().getValue();
    }

    public Vendedores toEntity(){
        Vendedores vendedor = new Vendedores(false);
        vendedor.getCodigo().setValue(this.getCodigo());
        vendedor.getNome().setValue(this.getNome());
        vendedor.getComissao().setValue(this.getComissao());
        return vendedor;
    }
}
