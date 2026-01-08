package br.com.rpinfo.lorenzo.core.application.dto;

import br.framework.classes.dto.annotations.Length;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Usuarios;

@Schema(description = "Dados do usuário")
@Getter
@Setter
public class UsuariosDto extends BaseDto {
    private Integer codigo;
    @Schema(description = "Nome do usuário", example = "Kallavan")
    private String nome;
    @Length(max = 1)
    @Schema(description = "Permissão de cadastros (S ou N)", example = "S")
    private String cadastros;
    @Length(max = 1)
    @Schema(description = "Permissão de entradas (S ou N)", example = "S")
    private String entradas;
    @Length(max = 1)
    @Schema(description = "Permissão de saídas (S ou N)", example = "S")
    private String saidas;
    @Length(max = 1)
    @Schema(description = "Permissão de cancelados (S ou N)", example = "S")
    private String cancelado;
    @Length(max = 1)
    @Schema(description = "Permissão de relatórios (S ou N)", example = "S")
    private String relatorio;
    @Length(max = 1)
    @Schema(description = "Permissão de configurações (S ou N)", example = "S")
    private String configuracoes;

    public UsuariosDto(){
        super();
    }

    public UsuariosDto(Usuarios usuarios){
        this.codigo = usuarios.getCodigo().getValue();
        this.nome = usuarios.getNome().getValue();
        this.cadastros = usuarios.getCadastros().getValue();
        this.entradas = usuarios.getEntradas().getValue();
        this.saidas = usuarios.getSaidas().getValue();
        this.cancelado = usuarios.getCancel().getValue();
        this.relatorio = usuarios.getRelat().getValue();
        this.configuracoes = usuarios.getConfig().getValue();
    }

    public Usuarios toEntity(){
        Usuarios usuario = new Usuarios(false);
        usuario.getCodigo().setValue(this.getCodigo());
        usuario.getNome().setValue(this.getNome());
        usuario.getCadastros().setValue(this.getCadastros());
        usuario.getEntradas().setValue(this.getEntradas());
        usuario.getSaidas().setValue(this.getSaidas());
        usuario.getCancel().setValue(this.getCancelado());
        usuario.getRelat().setValue(this.getRelatorio());
        usuario.getConfig().setValue(this.getConfiguracoes());
        return usuario;
    }
}
