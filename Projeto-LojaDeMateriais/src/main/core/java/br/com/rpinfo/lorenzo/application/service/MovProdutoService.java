package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.MovProdutosCabDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosD;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.movimentacoes.MovimentacoesDaoImp;

public class MovProdutoService extends ServiceBase {

    private MovimentacoesDao dao;

    public MovProdutoService(IConnection connection){
        super(connection);
        this.dao = new MovimentacoesDaoImp(connection);
    }

    public boolean adicionarEntradas (MovProdutosCabDto mvpcDto) throws Exception {
        try{
            if(mvpcDto == null) {
                throw new Exception("Os dados da movimentação são nulos.");
            }
            MovProdutosC mvpc = mvpcDto.toEntity();

            if(this.validarStatusMvPc(mvpc) && this.validarEntrada(mvpc) && this.validarTipoEntidade(mvpc)){
                for(MovProdutosD item : mvpc.getItens()){
                    if(!this.validarStatusMvPd(item)){
                        return false;
                    }
                }
                return this.dao.insertEntradas(mvpc);
            }

            return false;
        } catch (Exception e) {
            throw new Exception("Erro ao inserir entradas na movimentação: " + e.getMessage());
        }
    }

    public boolean adicionarSaidas (MovProdutosCabDto mvpcDto) throws Exception {
        try{
            if(mvpcDto == null) {
                throw new Exception("Os dados da movimentação são nulos.");
            }
            MovProdutosC mvpc = mvpcDto.toEntity();

            if(this.validarStatusMvPc(mvpc) && this.validarSaida(mvpc) && this.validarTipoEntidade(mvpc)){
                for(MovProdutosD item : mvpc.getItens()){
                    if(!this.validarStatusMvPd(item)){
                        return false;
                    }
                }
                return this.dao.insertSaidas(mvpc);
            }

            return false;
        } catch (Exception e) {
            throw new Exception("Erro ao inserir saidas na movimentação: " + e.getMessage());
        }
    }

    public boolean validarStatusMvPc(MovProdutosC mvpc){
        if(mvpc.getStatus().getValue() != null){
            return mvpc.getStatus().getValue().equals("N");
        }
        return false;
    }

    public boolean validarStatusMvPd(MovProdutosD mvpd){
        if(mvpd.getStatus().getValue() != null){
            return mvpd.getStatus().getValue().equals("N");
        }
        return false;
    }

    public boolean validarEntrada(MovProdutosC mvpc){
        if(mvpc.getEs().getValue() != null){
            return mvpc.getEs().getValue().equals("E");
        }
        return false;
    }

    public boolean validarSaida(MovProdutosC mvpc){
        if(mvpc.getEs().getValue() != null){
            return mvpc.getEs().getValue().equals("S");
        }
        return false;
    }

    public boolean validarTipoEntidade(MovProdutosC mvpc){
        if(mvpc.getTipoentidade().getValue() != null){
            return mvpc.getTipoentidade().getValue().equals("F") || mvpc.getTipoentidade().getValue().equals("J");
        }
        return false;
    }

}
