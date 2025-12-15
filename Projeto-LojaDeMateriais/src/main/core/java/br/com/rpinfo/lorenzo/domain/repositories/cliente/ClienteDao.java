package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.cliente;

import br.framework.classes.helpers.Types;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;
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
