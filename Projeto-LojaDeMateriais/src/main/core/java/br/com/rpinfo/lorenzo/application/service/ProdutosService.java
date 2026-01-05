package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class ProdutosService extends ServiceBase {
    private ProdutoDao dao;

    public ProdutosService(IConnection connection) {
        super(connection);
        this.dao = new ProdutoDaoImp(connection);
    }

    public boolean adicionarProduto(ProdutosDto produtosDto) throws ValidationException {
        try {
            if (produtosDto == null) {
                throw new ValidationException("Os dados do produto são nulos.");
            }

            //Garante que a embalagem seja em maiúsculo
            produtosDto.setEmbalagem(produtosDto.getEmbalagem().toUpperCase());

            if (verificaEmbalagem(produtosDto.getEmbalagem())) {
                Produtos produto = produtosDto.toEntity();
                //produto.getDtultcompra().setValue(data.getValue());
                //produto.getDtultvenda().setValue(data.getValue());

                if (this.dao.insertProduto(produto)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 40, "Gravação de um novo produto no banco de dados");
                    return true;
                }
            }
        } catch (Exception e) {
            throw new ValidationException("Erro ao adicionar um produto: " + e.getMessage());
        }
        return false;
    }

    public List<ProdutosDto> getListProdutos() throws Exception {
        List<Produtos> produtos = this.dao.getListProdutos();

        try {
            if (!produtos.isEmpty()) {
                DocumentoUtils.gravaLog(this.getConnection(), 42, "Consulta da lista de todos os produtos gravados no banco de dados");
                return produtos.stream().map(ProdutosDto::new).toList();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar a lista de produtos: " + e.getMessage());
        }
    }

    public ProdutosDto getProduto(Integer id) throws Exception {
        Produtos produto = this.dao.getProduto(id);

        try {
            if (produto != null) {
                DocumentoUtils.gravaLog(this.getConnection(), 42, "Consulta de um produto específico por ID");
                return produto.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar produto: " + e.getMessage());
        }
    }

    public boolean atualizarProduto(ProdutosDto prodDto) throws Exception {
        Produtos prod = this.dao.getProduto(prodDto.getCodigo());

        try {
            if (prod != null) {
                if (!Strings.isNullOrEmpty(prodDto.getDepartamento())) {
                    prod.getDpto().setValue(prodDto.getDepartamento());
                }
                if (!Strings.isNullOrEmpty(prodDto.getEmbalagem())) {
                    if (verificaEmbalagem(prodDto.getEmbalagem())) {
                        prod.getEmbalagem().setValue(prodDto.getEmbalagem());
                    } else{
                        return false;
                    }
                }
                if (prodDto.getQuantiaEmbalagem() >= 0) {
                    prod.getQembalagem().setValue(prodDto.getQuantiaEmbalagem());
                }
                if (prodDto.getPrecoCompra() >= 0) {
                    prod.getPrecocompra().setValue(prodDto.getPrecoCompra());
                }
                if (prodDto.getPrecoVenda() >= 0) {
                    prod.getPreconvenda().setValue(prodDto.getPrecoVenda());
                }
                if (prodDto.getEstoque() >= 0) {
                    prod.getEstoque().setValue(prodDto.getEstoque());
                }
                if (this.dao.update(prod)) {
                    DocumentoUtils.gravaLog(this.getConnection(), 41, "Edição de dados de um produto específico");
                    return true;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar produto: " + e.getMessage());
        }
        return false;
    }

    public boolean atualizarEstoque(Integer id, Double qtd) throws Exception {
        Produtos prod = this.dao.getProduto(id);
        try {
            if (prod != null) {
                prod.getEstoque().setValue(prod.getEstoque().getValue() + qtd);
            }
            return this.dao.update(prod);
        } catch (Exception e) {
            throw new ValidationException("Houve um erro ao atualizar o estoque: " + e.getMessage());
        }
    }

    public boolean verificaEmbalagem(String embalagem) {
        if (embalagem.equals("UN") || embalagem.equals("CX") || embalagem.equals("SC")
                || embalagem.equals("PC") || embalagem.equals("MT")) {
            return true;
        }
        return false;
    }
}
