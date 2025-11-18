package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import com.google.common.base.Strings;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Data;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDaoImp;

import java.util.Date;
import java.util.List;

public class ProdutosService extends ServiceBase {
    private ProdutoDao dao;

    public ProdutosService(IConnection connection) {
        super(connection);
        this.dao = new ProdutoDaoImp(connection);
    }

    public boolean adicionarProduto(ProdutosDto produtosDto) throws ValidationException {
        Data data = new Data();
        Date dateJava = new Date();
        data.setValue(dateJava);
        try {
            if (produtosDto == null) {
                throw new ValidationException("Os dados do produto são nulos.");
            }

            //Garante que a embalagem seja em maiúsculo
            produtosDto.setEmbalagem(produtosDto.getEmbalagem().toUpperCase());

            if(verificaEmbalagem(produtosDto.getEmbalagem())){
                Produtos produto = produtosDto.toEntity();
                produto.getDtultcompra().setValue(data.getValue());           //Como são duas coisas diferentes, ver quais datas colocar
                produto.getDtultvenda().setValue(data.getValue());           //Colocando apenas a data atual para ambos

                if (this.dao.insertProduto(produto)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return false;
    }

    public List<ProdutosDto> getListProdutos() throws Exception {
        List<Produtos> produtos = this.dao.getListProdutos();

        if (!produtos.isEmpty()) {
            return produtos.stream().map(ProdutosDto::new).toList();
        }
        return null;
    }

    public ProdutosDto getProduto(Integer id) throws Exception {
        Produtos produto = this.dao.getProduto(id);

        if (produto != null) {
            return produto.toDto();
        }

        return null;
    }

    public boolean atualizarProduto(ProdutosDto prodDto) throws Exception {
        Produtos prod = this.dao.getProduto(prodDto.getCodigo());

        try {
            if (prod != null) {
                if (Strings.isNullOrEmpty(prodDto.getDepartamento())) {
                    prod.getDpto().setValue(prodDto.getDepartamento());
                }
                if (Strings.isNullOrEmpty(prodDto.getEmbalagem())) {
                    prod.getEmbalagem().setValue(prodDto.getEmbalagem());
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
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar produto: " + e.getMessage());
        }
        return false;
    }

    public boolean verificaEmbalagem(String embalagem){
        if(embalagem.equals("UN") || embalagem.equals("CX") || embalagem.equals("SC")
                || embalagem.equals("PC") || embalagem.equals("MT")){
            return true;
        }
        return false;
    }
}
