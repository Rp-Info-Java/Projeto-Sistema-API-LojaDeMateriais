package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Configuracoes;

@Getter
@Setter
public class ConfiguracoesDto extends BaseDto {

    private Integer codigo;
    private String nomeEmpresa;
    private Double percentualDescontos;
    @Length(max = 1)
    private String validaSaidas;
    @Length(max = 1)
    private String validaFornecedor;
    @Length(max = 1)
    private String validaCliente;

    public ConfiguracoesDto(){
        super();
    }

    public ConfiguracoesDto(Configuracoes configuracao){
        this.codigo = configuracao.getCodigo().getValue();
        this.nomeEmpresa = configuracao.getNomeempresa().getValue();
        this.percentualDescontos = configuracao.getPercdescontos().getValue();
        this.validaSaidas = configuracao.getValidasaidas().getValue();
        this.validaFornecedor = configuracao.getValidafornec().getValue();
        this.validaCliente = configuracao.getValidacliente().getValue();
    }

    public Configuracoes toEntity(){
        Configuracoes configuracao = new Configuracoes(false);
        configuracao.getCodigo().setValue(this.getCodigo());
        configuracao.getNomeempresa().setValue(this.getNomeEmpresa());
        configuracao.getPercdescontos().setValue(this.getPercentualDescontos());
        configuracao.getValidasaidas().setValue(this.getValidaSaidas());
        configuracao.getValidafornec().setValue(this.getValidaFornecedor());
        configuracao.getValidacliente().setValue(this.getValidaCliente());
        return configuracao;
    }
}
