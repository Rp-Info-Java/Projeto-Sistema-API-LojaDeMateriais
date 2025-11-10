package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.fornecedores;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Fornecedores;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedoresDao{
    boolean insert (Fornecedores fornecedores) throws Exception;

    boolean update (Fornecedores fornecedores) throws Exception;

    Fornecedores getFornecedor (Integer id) throws Exception;

    List<Fornecedores> getListFornecedores () throws Exception;

    boolean getFornecedorCpfCnpj(String cpfCnpj) throws Exception;
}
