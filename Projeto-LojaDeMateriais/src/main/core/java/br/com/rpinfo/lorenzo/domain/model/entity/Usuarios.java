package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.UsuariosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "usuarios", prefix = "usua_")
public class Usuarios extends EntityClass implements Serializable {

    private Numerico codigo = new Numerico(true);;
    private Descricao nome = new Descricao(true);;
    private Descricao cadastros = new Descricao(true);;
    private Descricao entradas = new Descricao(true);;
    private Descricao saidas = new Descricao(true);;
    private Descricao cancel = new Descricao(true);;
    private Descricao relat = new Descricao(true);;
    private Descricao config = new Descricao(true);

    public Usuarios(){ super(); }

    public Usuarios(Boolean autoEnableFields){
        super();
    }

    public UsuariosDto toDto(){
        UsuariosDto usuaDto = new UsuariosDto();
        usuaDto.setCodigo(this.getCodigo().getValue());
        usuaDto.setNome(this.getNome().getValue());
        usuaDto.setCadastros(this.getCadastros().getValue());
        usuaDto.setEntradas(this.getEntradas().getValue());
        usuaDto.setSaidas(this.getSaidas().getValue());
        usuaDto.setCancelado(this.getCancel().getValue());
        usuaDto.setRelatorio(this.getRelat().getValue());
        usuaDto.setConfiguracoes(this.getConfig().getValue());
        return usuaDto;
    }
}
