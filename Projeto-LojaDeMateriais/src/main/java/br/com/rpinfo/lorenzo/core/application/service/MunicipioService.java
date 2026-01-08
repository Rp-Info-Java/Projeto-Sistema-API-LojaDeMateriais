package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import br.com.rpinfo.lorenzo.core.application.dto.MunicipiosDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Municipios;
import br.com.rpinfo.lorenzo.core.domain.repositories.municipios.MunicipiosDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.municipios.MunicipiosDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;

import java.util.List;

public class MunicipioService extends ServiceBase{
    private MunicipiosDao dao;

    public MunicipioService(IConnection connection){
        super(connection);
        this.dao = new MunicipiosDaoImp(connection);
    }

    public boolean adicionarMunicipio(MunicipiosDto municipiosDto) throws ValidationException {
        try{
            if(municipiosDto == null){
                throw new ValidationException("Os dados do município são nulos.");
            }
            Municipios municipios = municipiosDto.toEntity();
            municipios.getUf().setValue(municipios.getUf().getValue().toUpperCase());
            if(this.dao.insert(municipios)){
                DocumentoUtils.gravaLog(this.getConnection(), 80, "Gravação de um novo Município no banco de dados");
                return true;
            }
        }catch(Exception e){
            throw new ValidationException("Erro ao adicionar Municipio: " + e.getMessage());
        }
        return false;
    }

    public boolean atualizarMunicipio(MunicipiosDto municipios) throws Exception {
        Municipios muni = this.dao.getMunicipio(municipios.getCodigo());
        try{
            if(muni != null){
                if(!Strings.isNullOrEmpty(municipios.getNome())){
                    muni.getNome().setValue(municipios.getNome());
                }
                if(!Strings.isNullOrEmpty(municipios.getUnidadeFederativa())){
                    muni.getUf().setValue(municipios.getUnidadeFederativa());
                }
                if(this.dao.update(muni)){
                    DocumentoUtils.gravaLog(this.getConnection(), 81, "Edição de campos de um Município");
                    return true;
                }
            }
        }catch(NullPointerException e){
            throw new NullPointerException("Erro ao atualizar o município: " + e.getMessage());
        }
        return false;
    }

    public MunicipiosDto getMunicipioById(Integer id) throws Exception {
        Municipios muni = this.dao.getMunicipio(id);

        try{
            if(muni != null){
                DocumentoUtils.gravaLog(this.getConnection(), 82, "Consulta de um Município específico por ID");
                return muni.toDto();
            }

            return null;
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar município por ID: " + e.getMessage());
        }
    }

    public List<MunicipiosDto> getListMunicipios() throws Exception {
        List<Municipios> municipios = this.dao.getListMunicipios();

        try{
            if(!municipios.isEmpty()){
                DocumentoUtils.gravaLog(this.getConnection(), 82, "Consulta da lista de todos os Municípios gravados");
                return municipios.stream().map(MunicipiosDto::new).toList();
            }

            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar a lista de municípios: " + e.getMessage());
        }
    }

    public List<MunicipiosDto> getListMunicipiosByUf(String uf) throws Exception {
        List<Municipios> municipios = this.dao.getListMunicipiosByUf(uf);

        try{
            if(!municipios.isEmpty()){
                DocumentoUtils.gravaLog(this.getConnection(), 82, "Consulta da lista de Municípios da UF informada");
                return municipios.stream().map(MunicipiosDto::new).toList();
            }

            return null;
        } catch (Exception e){
            throw new ValidationException("Erro ao buscar município por UF: " + e.getMessage());
        }
    }
}
