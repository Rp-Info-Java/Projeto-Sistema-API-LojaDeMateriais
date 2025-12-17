package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MunicipiosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.MunicipioService;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;

public class MunicipiosUseCase extends MunicipioService {
    public MunicipiosUseCase(IConnection connection) { super(connection); }

    public static Response inserirMunicipio(MunicipiosDto municipio, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        MunicipioService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MunicipioService(connection);
            return ResponseHandler.ok(business.adicionarMunicipio(municipio), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir municipio: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaMunicipios(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        MunicipioService business;
        try{
            connection = ConnectionManager.newConnection();
            business = new MunicipioService(connection);
            return ResponseHandler.ok(business.getListMunicipios(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de municipios: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getMunicipio(MethodVersion methodVersion, Integer id) throws NullPointerException {
        IConnection connection = null;
        MunicipioService business;
        try{
            connection = ConnectionManager.newConnection();
            business = new MunicipioService(connection);
            return ResponseHandler.ok(business.getMunicipioById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar o municipio: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response updateMunicipio(MethodVersion methodVersion, MunicipiosDto municipiosDto) throws ValidationException {
        IConnection connection = null;
        MunicipioService business;
        try{
            connection = ConnectionManager.newConnection();
            business = new MunicipioService(connection);
            return ResponseHandler.ok(business.atualizarMunicipio(municipiosDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar o municipio: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaMunicipiosByUf(MethodVersion methodVersion, String uf) throws NullPointerException {
        IConnection connection = null;
        MunicipioService business;
        try{
            connection = ConnectionManager.newConnection();
            business = new MunicipioService(connection);
            return ResponseHandler.ok(business.getListMunicipiosByUf(uf), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de municipios por estado: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
