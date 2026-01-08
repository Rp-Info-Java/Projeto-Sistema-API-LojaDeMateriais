package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import br.com.rpinfo.lorenzo.core.application.dto.ClienteDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Cliente;
import br.com.rpinfo.lorenzo.core.domain.repositories.cliente.ClienteDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.cliente.ClienteDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;

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
            if ((cliente.getCpfcnpj() != null) && (cliente.getNome() != null)) {
                if (DocumentoUtils.validarTamanhoCpfCnpj(cliente.getCpfcnpj().getValue()) && (DocumentoUtils.validarSituacao(cliente.getSituacao().getValue())
                        && (DocumentoUtils.validarTipo(cliente.getTipo().getValue())))) {
                    if (this.verificaCpfcnpj(cliente.getCpfcnpj().getValue())) {
                        if (this.dao.insertCliente(cliente)) {
                            DocumentoUtils.gravaLog(this.getConnection(), 20, "Gravação de cliente no banco de dados");
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (ValidationException e) {
            throw new ValidationException("Erro ao adicionar cliente: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar CPF/CNPJ do cliente: " + e.getMessage());
        }
    }

    public boolean atualizarCliente(ClienteDto clienteDto) throws Exception {
        Cliente clie = this.dao.getCliente(clienteDto.getCodigo());
        try {
            if (clie != null) {
                if (!Strings.isNullOrEmpty(clienteDto.getSituacao())) {
                    if (DocumentoUtils.validarSituacao(clienteDto.getSituacao())) {
                        clie.getSituacao().setValue(clienteDto.getSituacao());
                    }
                }
                if (!Strings.isNullOrEmpty(clienteDto.getTipo())) {
                    if (DocumentoUtils.validarTipo(clienteDto.getTipo())) {
                        clie.getTipo().setValue(clienteDto.getTipo());
                    }
                }
                if (!Strings.isNullOrEmpty(clienteDto.getRua())) {
                    clie.getRua().setValue(clienteDto.getRua());
                }
                if (!Strings.isNullOrEmpty(clienteDto.getBairro())) {
                    clie.getBairro().setValue(clienteDto.getBairro());
                }
                if (!Strings.isNullOrEmpty(clienteDto.getNumero())) {
                    clie.getNumero().setValue(clienteDto.getNumero());
                }
                if (!Strings.isNullOrEmpty(clienteDto.getCep())) {
                    clie.getCep().setValue(clienteDto.getCep());
                }
                if (clienteDto.getCodigoMunicipio() != null) {
                    clie.getMuni_codigo().setValue(clienteDto.getCodigoMunicipio());
                }
                if (!Strings.isNullOrEmpty(clienteDto.getNomeMunicipio())) {
                    clie.getMuni_nome().setValue(clienteDto.getNomeMunicipio());
                }
                if (!Strings.isNullOrEmpty(clienteDto.getTelefone())) {
                    clie.getFone().setValue(clienteDto.getTelefone());
                }
                if (this.dao.updateCliente(clie)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 21, "Atualização de campos de informações do cliente no banco de dados");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar o cliente: " + e.getMessage());
        }
        return false;
    }

    public List<ClienteDto> getListClientes() throws Exception {
        List<Cliente> clientes = this.dao.getListClientes();

        try{
            if (!clientes.isEmpty()) {
                DocumentoUtils.gravaLog(this.getConnection(), 22, "Consulta da lista de clientes gravados no banco de dados");
                return clientes.stream().map(ClienteDto::new).toList();
            }
            return null;
        } catch (Exception e){
            throw new NullPointerException("Erro ao buscar lista de clientes: " + e.getMessage());
        }
    }

    public ClienteDto getClienteById(Integer id) throws Exception {
        Cliente cliente = this.dao.getCliente(id);

        try {
            if (cliente != null) {
                DocumentoUtils.gravaLog(this.getConnection(), 22, "Consulta de um cliente especifíco por ID");
                return cliente.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new ValidationException("Erro ao buscar cliente por ID: " + e.getMessage());
        }
    }

    public boolean verificaCpfcnpj(String cpfCnpj) throws Exception {
        if (this.dao.getClienteCpfCnpj(cpfCnpj)) {
            return false; //Retorna false quando o CPF já está inserido no banco de dados
        } else {
            return true;
        }
    }
}
