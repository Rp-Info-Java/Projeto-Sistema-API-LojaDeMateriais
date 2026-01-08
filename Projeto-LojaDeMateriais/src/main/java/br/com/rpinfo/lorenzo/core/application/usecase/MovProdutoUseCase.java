package br.com.rpinfo.lorenzo.core.application.usecase;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.Response;
import br.com.rpinfo.lorenzo.core.adapter.rest.response.ResponseHandler;
import br.com.rpinfo.lorenzo.core.application.dto.ConfiguracoesDto;
import br.com.rpinfo.lorenzo.core.application.dto.MovProdutosCabDto;
import br.com.rpinfo.lorenzo.core.application.service.ConfiguracoesService;
import br.com.rpinfo.lorenzo.core.application.service.MovProdutoService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.enums.MethodVersion;
import br.com.rpinfo.lorenzo.core.infrastructure.datasource.db.ConnectionManager;


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
