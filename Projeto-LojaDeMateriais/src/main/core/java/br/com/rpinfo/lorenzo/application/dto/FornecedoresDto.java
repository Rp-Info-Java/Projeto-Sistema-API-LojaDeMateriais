package main.core.java.br.com.rpinfo.lorenzo.application.dto;

import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.BaseDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Fornecedores;

@Getter
@Setter
public class FornecedoresDto extends BaseDto {
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
