package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.UsuariosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Usuarios;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.usuarios.UsuariosDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.usuarios.UsuariosDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class UsuariosService extends ServiceBase {

    private UsuariosDao dao;

    public UsuariosService(IConnection connection) {
        super(connection);
        this.dao = new UsuariosDaoImp(connection);
    }

    public List<UsuariosDto> getListUsuarios() throws Exception {
        List<Usuarios> usuarios = this.dao.getListUsuarios();

        try{
            if(!usuarios.isEmpty()){
                DocumentoUtils.gravaLog(this.getConnection(), 62, "Consulta de todos os usuários do sistema");
                return usuarios.stream().map(UsuariosDto::new).toList();
            }
            return null;
        }catch(Exception e){
            throw new ValidationException("Erro ao buscar lista de usuários: " + e.getMessage());
        }
    }

    public UsuariosDto getUsuarioById(Integer id) throws Exception {
        Usuarios usuario = this.dao.getUsuario(id);

        try{
            if(usuario != null){
                DocumentoUtils.gravaLog(this.getConnection(), 62, "Consulta de usuário específico por ID");
                return usuario.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar usuário por ID: " + e);
        }
    }

    //getUsuario para usar no ConnectionManager e salvar o Usuário do sistema
    public Usuarios getUsuarioEntityById(Integer id) throws Exception {
        return this.dao.getUsuario(id);
    }

    public boolean adicionarUsuario(UsuariosDto usuariosDto) throws Exception {
        try {
            if (usuariosDto == null) {
                throw new ValidationException("Os dados do usuário são nulos.");
            }
            Usuarios usuarios = usuariosDto.toEntity();
            if (usuarios.getNome().getValue() != null) {
                if (this.dao.insert(usuarios)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 60, "Gravação de um novo usuário no sistema");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new ValidationException("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public boolean atualizarUsuario(UsuariosDto usuariosDto) throws Exception {
        Usuarios usua = this.dao.getUsuario(usuariosDto.getCodigo());
        try {
            if (usua != null) {
                if (!Strings.isNullOrEmpty(usuariosDto.getNome())){
                    usua.getNome().setValue(usuariosDto.getNome());
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getCadastros())){
                    if(DocumentoUtils.validarCamposConfig(usuariosDto.getCadastros())){
                        usua.getCadastros().setValue(usuariosDto.getCadastros());
                    }
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getEntradas())){
                    if(DocumentoUtils.validarCamposConfig(usuariosDto.getEntradas())){
                        usua.getEntradas().setValue(usuariosDto.getEntradas());
                    }
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getSaidas())){
                    if(DocumentoUtils.validarCamposConfig(usuariosDto.getSaidas())){
                        usua.getSaidas().setValue(usuariosDto.getSaidas());
                    }
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getCancelado())){
                    if(DocumentoUtils.validarCamposConfig(usuariosDto.getCancelado())){
                        usua.getCancel().setValue(usuariosDto.getCancelado());
                    }
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getRelatorio())){
                    if(DocumentoUtils.validarCamposConfig(usuariosDto.getRelatorio())){
                        usua.getRelat().setValue(usuariosDto.getRelatorio());
                    }
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getConfiguracoes())){
                    if(DocumentoUtils.validarCamposConfig(usuariosDto.getCadastros())){
                        usua.getConfig().setValue(usuariosDto.getConfiguracoes());
                    }
                }
                if(this.dao.update(usua)){
                    DocumentoUtils.gravaLog(this.getConnection(), 61, "Atualização de dados de um usuário específico");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar o usuário: " + e.getMessage());
        }
        return false;
    }
}
