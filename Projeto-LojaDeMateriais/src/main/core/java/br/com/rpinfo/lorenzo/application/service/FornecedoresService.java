package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.FornecedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Fornecedores;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores.FornecedoresDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores.FornecedoresDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

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
                            DocumentoUtils.gravaLog(this.getConnection(), 30, "Gravação de fornecedor");
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (ValidationException e) {
            throw new ValidationException("Erro ao adicionar cliente: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        List<Fornecedores> fornecedores = this.dao.getListFornecedores();

        try{
            if(!fornecedores.isEmpty()){
                DocumentoUtils.gravaLog(this.getConnection(), 32, "Consulta de Fornecedores");
                return fornecedores.stream().map(FornecedoresDto::new).toList();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar a lista de fornecedores: " + e.getMessage());
        }
    }

    public FornecedoresDto getFornecedorById(Integer id) throws Exception{
        Fornecedores fornecedor = this.dao.getFornecedor(id);

        try{
            if(fornecedor != null){
                DocumentoUtils.gravaLog(this.getConnection(), 32, "Consulta de Fornecedor");
                return fornecedor.toDto();
            }
            return null;
        } catch (Exception e){
            throw new ValidationException("Erro ao buscar fornecedor por ID: " + e.getMessage());
        }
    }

    public boolean atualizarFornecedor(FornecedoresDto fornDto) throws Exception{
        Fornecedores forn = this.dao.getFornecedor(fornDto.getCodigo());
        try {
            if (forn != null) {
                if (!Strings.isNullOrEmpty(fornDto.getSituacao())) {
                    if(DocumentoUtils.validarSituacao(fornDto.getSituacao())){
                        forn.getSituacao().setValue(fornDto.getSituacao());
                    }
                }
                if (!Strings.isNullOrEmpty(fornDto.getTipo())) {
                    if(DocumentoUtils.validarTipo(fornDto.getTipo())){
                        forn.getTipo().setValue(fornDto.getTipo());
                    }
                }
                if (!Strings.isNullOrEmpty(fornDto.getRua())) {
                    forn.getRua().setValue(fornDto.getRua());
                }
                if (!Strings.isNullOrEmpty(fornDto.getBairro())) {
                    forn.getBairro().setValue(fornDto.getBairro());
                }
                if (!Strings.isNullOrEmpty(fornDto.getNumero())) {
                    forn.getNumero().setValue(fornDto.getNumero());
                }
                if (!Strings.isNullOrEmpty(fornDto.getCep())) {
                    forn.getCep().setValue(fornDto.getCep());
                }
                if (fornDto.getCodigoMunicipio() != null) {
                    forn.getMuni_codigo().setValue(fornDto.getCodigoMunicipio());
                }
                if (!Strings.isNullOrEmpty(fornDto.getNomeMunicipio())) {
                    forn.getMuni_nome().setValue(fornDto.getNomeMunicipio());
                }
                if (!Strings.isNullOrEmpty(fornDto.getTelefone())) {
                    forn.getFone().setValue(fornDto.getTelefone());
                }
                if(this.dao.update(forn)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 31, "Edição de dados de fornecedor");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar o fornecedor: " + e.getMessage());
        }
        return false;
    }
}
