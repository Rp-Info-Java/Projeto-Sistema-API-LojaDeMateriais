package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ClienteDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.cliente.ClienteDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.cliente.ClienteDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class ClienteService extends ServiceBase {

    private ClienteDao dao;

    public ClienteService(IConnection connection) {
        super(connection);
        this.dao = new ClienteDaoImp(connection);
    }

    public boolean adicionarCliente(ClienteDto clienteDto) throws ValidationException {
        try {
            if (clienteDto == null) {
                throw new ValidationException("Os dados do cliente são nulos.");
            }
            Cliente cliente = clienteDto.toEntity();
            if ((cliente.getCpfcnpj() != null) && (cliente.getNome() != null)){
                if (DocumentoUtils.validarTamanhoCpfCnpj(cliente.getCpfcnpj().getValue()) && (DocumentoUtils.validarSituacao(cliente.getSituacao().getValue())
                        && (DocumentoUtils.validarTipo(cliente.getTipo().getValue())))){
                    if (this.verificaCpfcnpj(cliente.getCpfcnpj().getValue())) {
                        if (this.dao.insertCliente(cliente)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new ValidationException("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public boolean atualizarCliente(ClienteDto clienteDto) throws Exception {
        Cliente clie = this.dao.getCliente(clienteDto.getCodigo());
        try {
            if (clie != null) {
                if (Strings.isNotEmpty(clienteDto.getSituacao())) {
                    if (DocumentoUtils.validarSituacao(clienteDto.getSituacao())) {
                        clie.getSituacao().setValue(clienteDto.getSituacao());
                    }
                }
                if (Strings.isNotEmpty(clienteDto.getTipo())) {
                    if (DocumentoUtils.validarTipo(clienteDto.getTipo())) {
                        clie.getTipo().setValue(clienteDto.getTipo());
                    }
                }
                if (Strings.isNotEmpty(clienteDto.getRua())) {
                    clie.getRua().setValue(clienteDto.getRua());
                }
                if (Strings.isNotEmpty(clienteDto.getBairro())) {
                    clie.getBairro().setValue(clienteDto.getBairro());
                }
                if (Strings.isNotEmpty(clienteDto.getNumero())) {
                    clie.getNumero().setValue(clienteDto.getNumero());
                }
                if (Strings.isNotEmpty(clienteDto.getCep())) {
                    clie.getCep().setValue(clienteDto.getCep());
                }
                if (clienteDto.getCodigoMunicipio() != null) {
                    clie.getMuni_codigo().setValue(clienteDto.getCodigoMunicipio());
                }
                if (Strings.isNotEmpty(clienteDto.getNomeMunicipio())) {
                    clie.getMuni_nome().setValue(clienteDto.getNomeMunicipio());
                }
                if (Strings.isNotEmpty(clienteDto.getTelefone())) {
                    clie.getFone().setValue(clienteDto.getTelefone());
                }
                if (this.dao.updateCliente(clie)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar o cliente: " + e.getMessage());
        }
        return false;
    }

    public List<ClienteDto> getListClientes() throws Exception {
        List<Cliente> clientes = this.dao.getListClientes();

        if (!clientes.isEmpty()) {
            return clientes.stream().map(ClienteDto::new).toList();
        }
        return null;
    }

    public ClienteDto getClienteById(Integer id) throws Exception {
        Cliente cliente = this.dao.getCliente(id);

        if (cliente != null) {
            return cliente.toDto();
        }

        return null;
    }

    public boolean verificaCpfcnpj(String cpfCnpj) throws Exception {
        if (this.dao.getClienteCpfCnpj(cpfCnpj)) {
            return false; //Retorna false quando o CPF já está inserido no banco de dados
        } else {
            return true;
        }
    }
}
