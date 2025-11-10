package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Configuracoes;

@Getter
@Setter
public class ConfiguracoesDto extends BaseDto {
    private String nomeempresa;
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

    public Configuracoes toEntity(){
        Configuracoes configuracao = new Configuracoes();
        configuracao.setNomeempresa(this.nomeempresa);
        configuracao.setPercdescontos(this.percentualDescontos);
        configuracao.setValidasaidas(this.validaSaidas);
        configuracao.setValidafornec(this.validaFornecedor);
        configuracao.setValidacliente(this.validaCliente);
        return configuracao;
    }
}
