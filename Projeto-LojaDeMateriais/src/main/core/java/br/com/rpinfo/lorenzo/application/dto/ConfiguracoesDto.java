package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Configuracoes;

@Schema(description = "Dados das configurações")
@Getter
@Setter
public class ConfiguracoesDto extends BaseDto {
    private Integer codigo;
    @Schema(description = "Nome da empresa", example = "Empresa Teste")
    private String nomeEmpresa;
    @Schema(description = "Percentual de descontos (Será aplicado em todas as movimentações)", example = "10")
    private Double percentualDescontos;
    @Length(max = 1)
    @Schema(description = "Validação de saídas (S ou N)", example = "N")
    private String validaSaidas;
    @Length(max = 1)
    @Schema(description = "Validação de fornecedores (S ou N)", example = "N")
    private String validaFornecedor;
    @Length(max = 1)
    @Schema(description = "Validação de clientes (S ou N)", example = "N")
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
