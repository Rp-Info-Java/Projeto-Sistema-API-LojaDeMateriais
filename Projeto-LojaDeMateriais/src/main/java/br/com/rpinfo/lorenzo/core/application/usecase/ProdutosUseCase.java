package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.ProdutosDto;
import br.com.rpinfo.lorenzo.core.application.service.ProdutosService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;

public class ProdutosUseCase extends ProdutosService {
    public ProdutosUseCase(IConnection connection) { super(connection); }

    public static Response inserirProduto(ProdutosDto produto, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.adicionarProduto(produto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir produto: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListaProdutos(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.getListProdutos(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar lista de produtos: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getProdutoById(Integer id, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.getProduto(id), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar produto pelo ID: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response updateProduto(ProdutosDto prodDto, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        ProdutosService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new ProdutosService(connection);
            return ResponseHandler.ok(business.atualizarProduto(prodDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar produto pelo ID: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }


}
