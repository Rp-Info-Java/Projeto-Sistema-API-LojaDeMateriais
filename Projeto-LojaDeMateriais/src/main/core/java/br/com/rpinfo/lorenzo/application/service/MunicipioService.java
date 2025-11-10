package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MunicipiosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Municipios;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.municipios.MunicipiosDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.municipios.MunicipiosDaoImp;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class MunicipioService extends ServiceBase{
    private MunicipiosDao dao;

    public MunicipioService(IConnection connection){
        super(connection);
        this.dao = new MunicipiosDaoImp(connection);
    }

    public boolean adicionarMunicipio(MunicipiosDto municipiosDto) throws Exception {
        try{
            if(municipiosDto == null){
                throw new ValidationException("Os dados do município são nulos.");
            }
            Municipios municipios = municipiosDto.toEntity();
            if(this.dao.insert(municipios)){
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
                if(Strings.isNotEmpty(municipios.getNome())){
                    muni.getNome().setValue(municipios.getNome());
                }
                if(Strings.isNotEmpty(municipios.getUnidadeFederativa())){
                    muni.getUf().setValue(municipios.getUnidadeFederativa());
                }
                if(this.dao.update(muni)){
                    return true;
                }
            }
        }catch(Exception e){
            throw new Exception("Erro ao atualizar o município: " + e.getMessage());
        }
        return false;
    }

    public MunicipiosDto getMunicipioById(Integer id) throws Exception {
        Municipios muni = this.dao.getMunicipio(id);

        if(muni != null){
            return muni.toDto();
        }

        return null;
    }

    public List<MunicipiosDto> getListMunicipios() throws Exception {
        List<Municipios> municipios = this.dao.getListMunicipios();

        if(!municipios.isEmpty()){
            return municipios.stream().map(MunicipiosDto::new).toList();
        }

        return null;
    }

    public List<MunicipiosDto> getListMunicipiosByUf(String uf) throws Exception {
        List<Municipios> municipios = this.dao.getListMunicipiosByUf(uf);

        if(!municipios.isEmpty()){
            return municipios.stream().map(MunicipiosDto::new).toList();
        }

        return null;
    }
}
