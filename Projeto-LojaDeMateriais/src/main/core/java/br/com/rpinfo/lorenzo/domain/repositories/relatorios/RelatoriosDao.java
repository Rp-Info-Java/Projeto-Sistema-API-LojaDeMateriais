package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.MovProdutosC;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatoriosDao {
    List<MovProdutosC> getEntradas () throws Exception;

    List<MovProdutosC> getSaidas() throws Exception; //_es

    List<MovProdutosC> getCanceladas() throws Exception; //virá do getStatus

    List<Vendedores> getComissoes() throws Exception; //virá do getComissoes do vendedores

    MovProdutosC getMovimentacaoEntraE(String transaction) throws Exception;
}
