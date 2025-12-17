package main.core.java.br.com.rpinfo.lorenzo.application.usecase;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.Response;
import main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response.ResponseHandler;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.application.service.ConfiguracoesService;
import main.core.java.br.com.rpinfo.lorenzo.application.service.MovProdutoService;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.enums.MethodVersion;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;


public class MovProdutoUseCase extends MovProdutoService {
    public MovProdutoUseCase(IConnection connection) { super(connection); }

    public static Response insertEntradas(MovProdutosCabDto mvpcDto, MethodVersion methodVersion) throws ValidationException {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            ConfiguracoesDto configDto = new ConfiguracoesService(connection).getConfiguracaoById(Integer.valueOf(mvpcDto.getNumeroDocumento()));
            return ResponseHandler.ok(business.adicionarEntradas(mvpcDto, configDto), methodVersion);
        } catch (Exception e) {
            throw new ValidationException("Erro ao inserir entradas na movimentação: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response insertSaidas(MovProdutosCabDto mvpcDto, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            ConfiguracoesDto configDto = new ConfiguracoesService(connection).getConfiguracaoById(Integer.valueOf(mvpcDto.getNumeroDocumento()));
            return ResponseHandler.ok(business.adicionarSaidas(mvpcDto, configDto), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao inserir saidas na movimentação: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getListMovimentacoesC(MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            return ResponseHandler.ok(business.getListMovimentacoesCD(), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar movimentações: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response cancelarMovimentacao(String transacao, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            return ResponseHandler.ok(business.cancelarMovimentacao(transacao), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao cancelar movimentação: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }

    public static Response getMovimentacaoByTransac(String transaction, MethodVersion methodVersion) throws NullPointerException {
        IConnection connection = null;
        MovProdutoService business;
        try {
            connection = ConnectionManager.newConnection();
            business = new MovProdutoService(connection);
            return ResponseHandler.ok(business.getMovimentacaoByTransaction(transaction), methodVersion);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao consultar movimentação: " + e.getMessage());
        } finally{
            if(connection != null){
                connection.close();
            }
        }
    }
}
