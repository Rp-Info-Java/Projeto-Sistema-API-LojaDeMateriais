package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.municipios;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Municipios;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface MunicipiosDao {
    boolean insert(Municipios municipios) throws SQLException;

    boolean update(Municipios municipios) throws Exception;

    Municipios getMunicipio(Integer id) throws Exception;

    List<Municipios> getListMunicipios() throws Exception;

    List<Municipios> getListMunicipiosByUf(String uf) throws Exception;
}
