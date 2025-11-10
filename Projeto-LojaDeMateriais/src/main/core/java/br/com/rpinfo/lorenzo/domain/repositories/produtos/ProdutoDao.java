package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoDao{
    boolean insertProduto (Produtos produto) throws Exception;

    boolean update (Produtos produto) throws Exception;

    Produtos getProduto (Long id) throws Exception;

    List<Produtos> getListProdutos () throws Exception;
}
