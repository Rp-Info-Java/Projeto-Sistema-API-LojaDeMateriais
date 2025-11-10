package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;

@Getter
@Setter
public class ClienteDto extends BaseDto {
    private Integer codigo;
    @Length(max = 1)
    private String situacao;
    private String nome;
    @Length(max = 1)
    private String tipo;
    @Length(max = 14)
    private String cpfcnpj;
    private String rua;
    private String bairro;
    private String numero;
    @Length(max = 8)
    private String cep;
    private Integer codigoMunicipio;
    private String nomeMunicipio;
    @Length(max = 15)
    private String telefone;

    public ClienteDto() {
        super();
    }

    public ClienteDto(Cliente cliente){
        this.codigo = cliente.getCodigo().getValue();
        this.situacao = cliente.getSituacao().getValue();
        this.nome = cliente.getNome().getValue();
        this.tipo = cliente.getTipo().getValue();
        this.cpfcnpj = cliente.getCpfcnpj().getValue();
        this.rua = cliente.getRua().getValue();
        this.bairro = cliente.getBairro().getValue();
        this.numero = cliente.getNumero().getValue();
        this.cep = cliente.getCep().getValue();
        this.codigoMunicipio = cliente.getMuni_codigo().getValue();
        this.nomeMunicipio = cliente.getMuni_nome().getValue();
        this.telefone = cliente.getFone().getValue();
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente(false);
        cliente.getCodigo().setValue(this.getCodigo());
        cliente.getSituacao().setValue(this.getSituacao());
        cliente.getNome().setValue(this.getNome());
        cliente.getTipo().setValue(this.getTipo());
        cliente.getCpfcnpj().setValue(this.getCpfcnpj());
        cliente.getRua().setValue(this.getRua());
        cliente.getBairro().setValue(this.getBairro());
        cliente.getNumero().setValue(this.getNumero());
        cliente.getCep().setValue(this.getCep());
        cliente.getMuni_codigo().setValue(this.getCodigoMunicipio());
        cliente.getMuni_nome().setValue(this.getNomeMunicipio());
        cliente.getFone().setValue(this.getTelefone());
        return cliente;
    }
}
