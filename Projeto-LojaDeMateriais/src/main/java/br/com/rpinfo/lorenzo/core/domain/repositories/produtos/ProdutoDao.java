package br.com.rpinfo.lorenzo.core.domain.repositories.produtos;

import br.com.rpinfo.lorenzo.core.domain.model.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ProdutoDao{
    boolean insertProduto (Produtos produto) throws SQLException;

    boolean update (Produtos produto) throws Exception;

    Produtos getProduto (Integer id) throws Exception;

    List<Produtos> getListProdutos () throws Exception;
}
