package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.VendedoresDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Vendedores;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores.VendedoresDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.vendedores.VendedoresDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class VendedoresService extends ServiceBase {

    private VendedoresDao dao;

    public VendedoresService(IConnection connection) {
        super(connection);
        this.dao = new VendedoresDaoImp(connection);
    }

    public boolean adicionarVendedor(VendedoresDto vendedorDto) throws ValidationException {
        try {
            if (vendedorDto == null) {
                throw new Exception("Os dados do vendedor são nulos.");
            }
            Vendedores vendedor = vendedorDto.toEntity();
            if (vendedor.getNome().getValue() != null) {
                if (this.dao.insert(vendedor)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 90, "Gravação de vendedor");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new ValidationException("Erro ao adicionar vendedor: " + e.getMessage());
        }
    }

    public List<VendedoresDto> getListVendedores() throws Exception {
        List<Vendedores> vend = this.dao.getListVendedores();

        if (!vend.isEmpty()) {
            DocumentoUtils.gravaLog(this.getConnection(), 92, "Consulta de vendedores");
            return vend.stream().map(VendedoresDto::new).toList();
        }
        return null;
    }

    public VendedoresDto getVendedorById(Integer id) throws Exception {
        Vendedores vendedor = this.dao.getVendedor(id);

        try {
            if (vendedor != null) {
                DocumentoUtils.gravaLog(this.getConnection(), 92, "Consulta de vendedor");
                return vendedor.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar vendedor pelo ID: " + e.getMessage());
        }
    }

    public boolean atualizarVendedor(VendedoresDto vendedorDto) throws Exception {
        Vendedores vendedor = this.dao.getVendedor(vendedorDto.getCodigo());
        try {
            if (vendedor != null) {
                if (!Strings.isNullOrEmpty(vendedorDto.getNome())) {
                    vendedor.getNome().setValue(vendedorDto.getNome());
                }
                if (vendedorDto.getComissao() != null) {
                    vendedor.getComissao().setValue(vendedorDto.getComissao());
                }
                if (this.dao.update(vendedor)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 91, "Edição de dados de vendedor");
                    return true;
                }
            }
        } catch (Exception e) {
            throw new NullPointerException("Erro ao atualizar vendedor: " + e.getMessage());
        }
        return false;
    }
}
