package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.FornecedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Fornecedores;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores.FornecedoresDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores.FornecedoresDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class FornecedoresService extends ServiceBase{
    private FornecedoresDao dao;

    public FornecedoresService(IConnection connection){
        super(connection);
        this.dao = new FornecedoresDaoImp(connection);
    }

    public boolean adicionarFornecedor(FornecedoresDto fornDto) throws ValidationException{
        try {
            if (fornDto == null) {
                throw new ValidationException("Os dados do fornecedor são nulos.");
            }
            Fornecedores forn = fornDto.toEntity();
            if ((forn.getCpfcnpj() != null) && (forn.getSituacao() != null) && (forn.getNome() != null) && (forn.getTipo() != null)) {
                if (DocumentoUtils.validarTamanhoCpfCnpj(forn.getCpfcnpj().getValue()) && DocumentoUtils.validarTipo(forn.getTipo().getValue())
                        && DocumentoUtils.validarSituacao(forn.getSituacao().getValue())) {
                    if (this.verificaCpfcnpj(forn.getCpfcnpj().getValue())) {
                        if(this.dao.insert(forn)){
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new ValidationException("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public boolean verificaCpfcnpj(String cpfCnpj) throws Exception {
        if (this.dao.getFornecedorCpfCnpj(cpfCnpj)) {
            return false; //Retorna false quando o CPF já está inserido no banco de dados
        } else {
            return true;
        }
    }

    public List<FornecedoresDto> getListFornecedores() throws Exception {
        return this.dao.getListFornecedores().stream().map(FornecedoresDto::new).toList();
    }

    public FornecedoresDto getFornecedorById(Integer id) throws Exception{
        return this.dao.getFornecedor(id).toDto();
    }

    public boolean atualizarFornecedor(FornecedoresDto fornDto) throws Exception{
        Fornecedores forn = this.dao.getFornecedor(fornDto.getCodigo());
        try {
            if (forn != null) {
                if (Strings.isNotEmpty(fornDto.getSituacao())) {
                    if(DocumentoUtils.validarSituacao(fornDto.getSituacao())){
                        forn.getSituacao().setValue(fornDto.getSituacao());
                    }
                }
                if (Strings.isNotEmpty(fornDto.getTipo())) {
                    if(DocumentoUtils.validarTipo(fornDto.getTipo())){
                        forn.getTipo().setValue(fornDto.getTipo());
                    }
                }
                if (Strings.isNotEmpty(fornDto.getRua())) {
                    forn.getRua().setValue(fornDto.getRua());
                }
                if (Strings.isNotEmpty(fornDto.getBairro())) {
                    forn.getBairro().setValue(fornDto.getBairro());
                }
                if (Strings.isNotEmpty(fornDto.getNumero())) {
                    forn.getNumero().setValue(fornDto.getNumero());
                }
                if (Strings.isNotEmpty(fornDto.getCep())) {
                    forn.getCep().setValue(fornDto.getCep());
                }
                if (fornDto.getCodigoMunicipio() != null) {
                    forn.getMuni_codigo().setValue(fornDto.getCodigoMunicipio());
                }
                if (Strings.isNotEmpty(fornDto.getNomeMunicipio())) {
                    forn.getMuni_nome().setValue(fornDto.getNomeMunicipio());
                }
                if (Strings.isNotEmpty(fornDto.getTelefone())) {
                    forn.getFone().setValue(fornDto.getTelefone());
                }
                if(this.dao.update(forn)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar o fornecedor: " + e.getMessage());
        }
        return false;
    }
}
