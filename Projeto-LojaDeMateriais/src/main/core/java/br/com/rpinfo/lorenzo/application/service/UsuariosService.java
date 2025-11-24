package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.UsuariosDto;
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
        return this.dao.getListUsuarios().stream().map(UsuariosDto::new).toList();
    }

    public UsuariosDto getUsuarioById(Integer id) throws Exception {
        return this.dao.getUsuario(id).toDto();
    }

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
                    usua.getCadastros().setValue(usuariosDto.getCadastros());
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getEntradas())){
                    usua.getEntradas().setValue(usuariosDto.getEntradas());
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getSaidas())){
                    usua.getCancel().setValue(usuariosDto.getCancelado());
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getRelatorio())){
                    usua.getRelat().setValue(usuariosDto.getRelatorio());
                }
                if(!Strings.isNullOrEmpty(usuariosDto.getConfiguracoes())){
                    usua.getConfig().setValue(usuariosDto.getConfiguracoes());
                }
                DocumentoUtils.gravaLog(this.getConnection(), 6, "Atualização de usuário");
                if(this.dao.update(usua)){
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar o usuário: " + e.getMessage());
        }
        return false;
    }
}
