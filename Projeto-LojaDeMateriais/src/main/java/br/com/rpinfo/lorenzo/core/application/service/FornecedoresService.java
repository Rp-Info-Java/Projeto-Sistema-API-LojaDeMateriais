package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import br.com.rpinfo.lorenzo.core.application.dto.FornecedoresDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Fornecedores;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Municipios;
import br.com.rpinfo.lorenzo.core.domain.repositories.fornecedores.FornecedoresDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.fornecedores.FornecedoresDaoImp;
import br.com.rpinfo.lorenzo.core.domain.repositories.municipios.MunicipiosDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.municipios.MunicipiosDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;

import java.util.List;

public class FornecedoresService extends ServiceBase{
    private FornecedoresDao dao;
    private MunicipiosDao daoMuni;

    public FornecedoresService(IConnection connection){
        super(connection);
        this.dao = new FornecedoresDaoImp(connection);
        this.daoMuni = new MunicipiosDaoImp(connection);
    }

    public boolean adicionarFornecedor(FornecedoresDto fornDto) throws ValidationException{
        try {
            if (fornDto == null) {
                throw new ValidationException("Os dados do fornecedor são nulos.");
            }
            Fornecedores forn = fornDto.toEntity();
            if ((forn.getCpfcnpj() != null) && (forn.getSituacao() != null) && (forn.getNome() != null) && (forn.getTipo() != null)) {
                if (DocumentoUtils.validarTamanhoCpfCnpj(forn.getCpfcnpj().getValue()) && DocumentoUtils.validarTipo(forn.getTipo().getValue())) {
                    if (this.verificaCpfcnpj(forn.getCpfcnpj().getValue())) {
                        forn.getSituacao().setValue("N");
                        Municipios muni = this.daoMuni.getMunicipio(fornDto.getCodigoMunicipio());
                        if(muni != null){
                            forn.getMuni_codigo().setValue(muni.getCodigo().getValue());
                            forn.getMuni_nome().setValue(muni.getNome().getValue());
                        } else{
                            return false;
                        }
                        if(this.dao.insert(forn)){
                            DocumentoUtils.gravaLog(this.getConnection(), 30, "Gravação de um novo fornecedor no banco de dados");
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
                DocumentoUtils.gravaLog(this.getConnection(), 32, "Consulta da lista com todos os fornecedores disponíveis no sistema");
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
                DocumentoUtils.gravaLog(this.getConnection(), 32, "Consulta de um fornecedor específico pelo seu ID");
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
                    DocumentoUtils.gravaLog(this.getConnection(), 31, "Edição de campo(s) de um fornecedor específico");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar o fornecedor: " + e.getMessage());
        }
        return false;
    }
}
