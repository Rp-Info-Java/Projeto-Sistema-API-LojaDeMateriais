package main.core.java.br.com.rpinfo.lorenzo.domain.model.entity;

import br.framework.annotations.TableAnnotation;
import br.framework.classes.DataBase.EntityClass;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.FornecedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Descricao;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Numerico;

import java.io.Serializable;

@Getter
@Setter
@TableAnnotation(tableName = "Fornecedores", prefix = "forn_")
public class Fornecedores extends EntityClass implements Serializable {

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

    public Fornecedores(){
        super();
    }

    public Fornecedores(Boolean autoEnableFields) { super(); }

    public FornecedoresDto toDto(){
        FornecedoresDto fornDto = new FornecedoresDto();
        fornDto.setCodigo(this.getCodigo().getValue());
        fornDto.setSituacao(this.getSituacao().getValue());
        fornDto.setNome(this.getNome().getValue());
        fornDto.setTipo(this.getTipo().getValue());
        fornDto.setCpfcnpj(this.getCpfcnpj().getValue());
        fornDto.setRua(this.getRua().getValue());
        fornDto.setBairro(this.getBairro().getValue());
        fornDto.setNumero(this.getNumero().getValue());
        fornDto.setCep(this.getCep().getValue());
        fornDto.setCodigoMunicipio(this.getMuni_codigo().getValue());
        fornDto.setNomeMunicipio(this.getMuni_nome().getValue());
        fornDto.setTelefone(this.getFone().getValue());
        return fornDto;
    }
}
