package br.com.rpinfo.lorenzo.core.domain.repositories.cliente;

import br.framework.classes.helpers.Types;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface ClienteDao {

    boolean insertCliente (Cliente cliente) throws SQLException, ValidationException;

    boolean updateCliente (Cliente cliente) throws Exception;

    Cliente getCliente (Integer id) throws Exception;

    List<Cliente> getListClientes () throws Exception;

    boolean getClienteCpfCnpj(String cpfCnpj) throws Exception;
}
