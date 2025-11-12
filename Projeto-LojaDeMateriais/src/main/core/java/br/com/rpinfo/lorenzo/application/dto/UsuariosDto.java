package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;

@Getter
@Setter
public class UsuariosDto extends BaseDto {
    private Integer codigo;
    private String nome;
    @Length(max = 1)
    private String cadastros;
    @Length(max = 1)
    private String entradas;
    @Length(max = 1)
    private String saidas;
    @Length(max = 1)
    private String cancelado;
    @Length(max = 1)
    private String relatorio;
    @Length(max = 1)
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
