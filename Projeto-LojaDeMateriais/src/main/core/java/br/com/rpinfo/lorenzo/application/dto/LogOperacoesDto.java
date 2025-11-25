package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.LogOperacoes;

import java.util.Date;

@Getter
@Setter
public class LogOperacoesDto extends BaseDto {
    private Integer codigo;
    private Date data;
    @Length(max=8)
    private String hora;
    private Integer codigoUsuario;
    @Length(max=255)
    private String descricao;

    public LogOperacoesDto(){super();}

    public LogOperacoesDto(LogOperacoes logOperacoes){
        this.codigo = logOperacoes.getCodigo().getValue();
        this.data = logOperacoes.getData().getValue();
        this.hora = logOperacoes.getHora().getValue();
        this.codigoUsuario = logOperacoes.getUsua_codigo().getValue();
        this.descricao = logOperacoes.getDescricao().getValue();
    }

    public LogOperacoes toEntity(){
        LogOperacoes logOperacoes = new LogOperacoes(false);
        logOperacoes.getCodigo().setValue(this.getCodigo());
        logOperacoes.getData().setValue(this.getData());
        logOperacoes.getHora().setValue(this.getHora());
        logOperacoes.getUsua_codigo().setValue(this.getCodigoUsuario());
        logOperacoes.getDescricao().setValue(this.getDescricao());
        return logOperacoes;
    }
}
