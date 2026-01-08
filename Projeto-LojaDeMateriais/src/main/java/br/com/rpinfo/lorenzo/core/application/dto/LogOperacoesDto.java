package br.com.rpinfo.lorenzo.core.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.LogOperacoes;

import java.util.Date;

@Schema(description = "Dados do log de operações")
@Getter
@Setter
public class LogOperacoesDto extends BaseDto {
    @Schema(description = "Código da operação realizada (ex: 30-39 = Operações com fornecedor, 50-59 = Operações com movimentações, etc.", example = "32")
    private Integer codigo;
    private Date data;
    @Length(max=8)
    private String hora;
    @Schema(description = "Código do usuario que efetivou a ação", example = "1")
    private Integer codigoUsuario;
    @Length(max=255)
    @Schema(description = "Descrição da operação", example = "Adicionado um novo usuário ao sistema")
    private String descricao;
    private String agrupamento;

    public LogOperacoesDto(){super();}

    public LogOperacoesDto(LogOperacoes logOperacoes){
        this.codigo = logOperacoes.getCodigo().getValue();
        this.data = logOperacoes.getData().getValue();
        this.hora = logOperacoes.getHora().getValue();
        this.codigoUsuario = logOperacoes.getUsua_codigo().getValue();
        this.descricao = logOperacoes.getDescricao().getValue();
        this.agrupamento = logOperacoes.getAgrupamento().getValue();
    }

    public LogOperacoes toEntity(){
        LogOperacoes logOperacoes = new LogOperacoes(false);
        logOperacoes.getCodigo().setValue(this.getCodigo());
        logOperacoes.getData().setValue(this.getData());
        logOperacoes.getHora().setValue(this.getHora());
        logOperacoes.getUsua_codigo().setValue(this.getCodigoUsuario());
        logOperacoes.getDescricao().setValue(this.getDescricao());
        logOperacoes.getAgrupamento().setValue(this.getAgrupamento());
        return logOperacoes;
    }
}
