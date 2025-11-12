package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.ProdutosService;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;

public class ProdutosUseCase extends ProdutosService {
    public ProdutosUseCase(IConnection connection) { super(connection); }

    public static Response inserirProduto(ProdutosDto produto, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.adicionarProduto(produto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaProdutos(MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.getListProdutos(), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de produtos: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getProdutoById(Integer id, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.getProduto(id), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produto pelo ID: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response updateProduto(ProdutosDto prodDto, MethodVersion methodVersion) throws SQLException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.atualizarProduto(prodDto), methodVersion);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produto pelo ID: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }


}
