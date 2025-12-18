package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do cliente")
@Getter
@Setter
public class ClienteDto extends BaseDto {
    private Integer codigo;
    @Length(max = 1)
    @Schema(description = "Situação do cliente (N ou I)", example = "N")
    private String situacao;
    @Schema(description = "Nome do cliente", example = "João da Silva")
    private String nome;
    @Length(max = 1)
    @Schema(description = "Tipo do cliente (F ou J)", example = "F")
    private String tipo;
    @Length(max = 14)
    @Schema(description = "CPF ou CNPJ do cliente", example = "11841715177")
    private String cpfcnpj;
    @Schema(description = "Rua do cliente", example = "Rua dos Bobos")
    private String rua;
    @Schema(description = "Bairro do cliente", example = "Bairro dos bobos")
    private String bairro;
    @Schema(description = "Número do cliente", example = "123")
    private String numero;
    @Length(max = 8)
    @Schema(description = "CEP do cliente", example = "12345678")
    private String cep;
    @Schema(description = "Código do município (deve existir no banco de dados)", example = "1")
    private Integer codigoMunicipio;
    @Schema(description = "Município do cliente", example = "Pindamonhangaba")
    private String nomeMunicipio;
    @Length(max = 15)
    @Schema(description = "Telefone do cliente", example = "11999999999")
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
