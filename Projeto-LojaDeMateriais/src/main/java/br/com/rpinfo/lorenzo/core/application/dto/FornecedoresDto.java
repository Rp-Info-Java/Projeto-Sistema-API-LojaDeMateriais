package br.com.rpinfo.lorenzo.core.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Fornecedores;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do fornecedor")
@Getter
@Setter
public class FornecedoresDto extends BaseDto {
    private Integer codigo;
    @Length(max = 1)
    @Schema(description = "Situação do fornecedor (N ou I)", example = "N")
    private String situacao;
    @Schema(description = "Nome do fornecedor", example = "JoaozinDuGrau")
    private String nome;
    @Length(max = 1)
    @Schema(description = "Fornecedor é pessoa física ou jurídica (F ou J)", example = "J")
    private String tipo;
    @Length(max = 14)
    @Schema(description = "CPF ou CNPJ do fornecedor", example = "11841715177")
    private String cpfcnpj;
    @Schema(description = "Rua do fornecedor", example = "Rua dos Mestres")
    private String rua;
    @Schema(description = "Bairro do fornecedor", example = "Bairro dos Mestres")
    private String bairro;
    @Schema(description = "Número do fornecedor", example = "123")
    private String numero;
    @Length(max = 8)
    @Schema(description = "CEP do fornecedor", example = "12345678")
    private String cep;
    @Schema(description = "Código do município (deve existir no banco de dados)", example = "1")
    private Integer codigoMunicipio;
    @Schema(description = "Município do fornecedor", example = "Pindamonhangaba")
    private String nomeMunicipio;
    @Length(max = 15)
    @Schema(description = "Telefone do fornecedor", example = "11999999999")
    private String telefone;

    public FornecedoresDto(){
        super();
    }

    public FornecedoresDto(Fornecedores fornecedor){
        this.codigo = fornecedor.getCodigo().getValue();
        this.situacao = fornecedor.getSituacao().getValue();
        this.nome = fornecedor.getNome().getValue();
        this.tipo = fornecedor.getTipo().getValue();
        this.cpfcnpj = fornecedor.getCpfcnpj().getValue();
        this.rua = fornecedor.getRua().getValue();
        this.bairro = fornecedor.getBairro().getValue();
        this.numero = fornecedor.getNumero().getValue();
        this.cep = fornecedor.getCep().getValue();
        this.codigoMunicipio = fornecedor.getMuni_codigo().getValue();
        this.nomeMunicipio = fornecedor.getMuni_nome().getValue();
        this.telefone = fornecedor.getFone().getValue();
    }

    public Fornecedores toEntity(){
        Fornecedores fornecedor = new Fornecedores(false);
        fornecedor.getCodigo().setValue(this.getCodigo());
        fornecedor.getSituacao().setValue(this.getSituacao());
        fornecedor.getNome().setValue(this.getNome());
        fornecedor.getTipo().setValue(this.getTipo());
        fornecedor.getCpfcnpj().setValue(this.getCpfcnpj());
        fornecedor.getRua().setValue(this.getRua());
        fornecedor.getBairro().setValue(this.getBairro());
        fornecedor.getNumero().setValue(this.getNumero());
        fornecedor.getCep().setValue(this.getCep());
        fornecedor.getMuni_codigo().setValue(this.getCodigoMunicipio());
        fornecedor.getMuni_nome().setValue(this.getNomeMunicipio());
        fornecedor.getFone().setValue(this.getTelefone());
        return fornecedor;
    }
}
