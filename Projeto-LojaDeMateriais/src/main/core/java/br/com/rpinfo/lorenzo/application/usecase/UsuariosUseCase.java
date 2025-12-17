package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.UsuariosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.UsuariosService;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

public class UsuariosUseCase extends UsuariosService {
    public UsuariosUseCase(IConnection connection) { super(connection); }

    public static Response inserirUsuario(UsuariosDto usuaDto, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        try {
            connection = ConnectionManager.newConnection();
            UsuariosService business = new UsuariosService(connection);
            return ResponseHandler.ok(business.adicionarUsuario(usuaDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir usuario: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaUsuarios(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        try {
            connection = ConnectionManager.newConnection();
            UsuariosService business = new UsuariosService(connection);
            ConnectionManager.getInstance().getUsuario();
            return ResponseHandler.ok(business.getListUsuarios(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de usuarios: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getUsuario(Integer id, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        try {
            connection = ConnectionManager.newConnection();
            UsuariosService business = new UsuariosService(connection);
            return ResponseHandler.ok(business.getUsuarioById(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar usuario: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response atualizarUsuario(UsuariosDto usuaDto, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        try {
            connection = ConnectionManager.newConnection();
            UsuariosService business = new UsuariosService(connection);
            return ResponseHandler.ok(business.atualizarUsuario(usuaDto), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao atualizar usuario: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
