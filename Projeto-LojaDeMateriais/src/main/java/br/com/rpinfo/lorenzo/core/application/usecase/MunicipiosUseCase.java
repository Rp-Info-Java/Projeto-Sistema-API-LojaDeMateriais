package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.MunicipiosDto;
import br.com.rpinfo.lorenzo.core.application.service.MunicipioService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

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
