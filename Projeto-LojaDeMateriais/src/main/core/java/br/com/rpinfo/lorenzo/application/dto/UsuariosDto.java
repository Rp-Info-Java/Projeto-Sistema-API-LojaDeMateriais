package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;

@Getter
@Setter
public class UsuariosDto extends BaseDto {
    private Long codigo;
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

    public Usuarios toEntity(){
        Usuarios usuario = new Usuarios();
        usuario.setCodigo(this.codigo);
        usuario.setNome(this.nome);
        usuario.setCadastros(this.cadastros);
        usuario.setEntradas(this.entradas);
        usuario.setSaidas(this.saidas);
        usuario.setCancel(this.cancelado);
        usuario.setRelat(this.relatorio);
        usuario.setConfig(this.configuracoes);
        return usuario;
    }
}
