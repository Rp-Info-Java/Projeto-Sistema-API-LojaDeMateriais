package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.MovProdutoService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.util.List;

public class MovProdutoUseCase extends MovProdutoService {
    public MovProdutoUseCase(IConnection connection) { super(connection); }

    public static Response insertEntradas(MovProdutosCabDto mvpcDto, MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            return ResponseHandler.ok(business.adicionarEntradas(mvpcDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir entradas na movimentação: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response insertSaidas(MovProdutosCabDto mvpcDto, MethodVersion methodVersion) throws Exception {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            return ResponseHandler.ok(business.adicionarSaidas(mvpcDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir saidas na movimentação: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
