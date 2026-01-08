package br.com.rpinfo.lorenzo.core.domain.repositories.fornecedores;

import br.com.rpinfo.lorenzo.core.domain.model.entity.Fornecedores;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface FornecedoresDao{
    boolean insert (Fornecedores fornecedores) throws SQLException;

    boolean update (Fornecedores fornecedores) throws Exception;

    Fornecedores getFornecedor (Integer id) throws Exception;

    List<Fornecedores> getListFornecedores () throws Exception;

    boolean getFornecedorCpfCnpj(String cpfCnpj) throws Exception;
}
