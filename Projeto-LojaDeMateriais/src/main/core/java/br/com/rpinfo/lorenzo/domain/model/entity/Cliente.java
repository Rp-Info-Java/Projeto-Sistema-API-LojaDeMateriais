package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ClienteDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "Cliente", prefix = "clie_")
public class Cliente extends EntityClass implements Serializable {
    
    private Numerico codigo = new Numerico(true);
    private Descricao situacao = new Descricao(true);
    private Descricao nome = new Descricao(true);
    private Descricao tipo = new Descricao(true);
    private Descricao cpfcnpj = new Descricao(true);
    private Descricao rua = new Descricao(true);
    private Descricao bairro = new Descricao(true);
    private Descricao numero = new Descricao(true);
    private Descricao cep = new Descricao(true);
    private Numerico muni_codigo = new Numerico(true);
    private Descricao muni_nome = new Descricao(true);
    private Descricao fone = new Descricao(true);

    public Cliente(){
        super();
    }

    public Cliente(Boolean autoEnableFields){
        super();
    }

    public ClienteDto toDto(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCodigo(this.getCodigo().getValue());
        clienteDto.setSituacao(this.getSituacao().getValue());
        clienteDto.setNome(this.getNome().getValue());
        clienteDto.setTipo(this.getTipo().getValue());
        clienteDto.setCpfcnpj(this.getCpfcnpj().getValue());
        clienteDto.setRua(this.getRua().getValue());
        clienteDto.setBairro(this.getBairro().getValue());
        clienteDto.setNumero(this.getNumero().getValue());
        clienteDto.setCep(this.getCep().getValue());
        clienteDto.setCodigoMunicipio(this.getMuni_codigo().getValue());
        clienteDto.setNomeMunicipio(this.getMuni_nome().getValue());
        clienteDto.setTelefone(this.getFone().getValue());
        return clienteDto;
    }
}
