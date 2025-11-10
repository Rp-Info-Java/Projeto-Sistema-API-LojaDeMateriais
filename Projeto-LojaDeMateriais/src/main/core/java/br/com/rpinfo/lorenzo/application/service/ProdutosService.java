package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ProdutosDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.field.Data;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos.ProdutoDaoImp;

import java.util.List;

public class ProdutosService extends ServiceBase {
    private ProdutoDao dao;

    public ProdutosService(IConnection connection) {
        super(connection);
        this.dao = new ProdutoDaoImp(connection);
    }

    public boolean adicionarProduto(ProdutosDto produtosDto) throws ValidationException {
        Data data = new Data();
        try {
            if (produtosDto == null) {
                throw new ValidationException("Os dados do produto são nulos.");
            }
            Produtos produto = produtosDto.toEntity();
            produto.getDtultcompra().setValue(data.getValue());           //Como são duas coisas diferentes, ver quais datas colocar
            produto.getDtultvenda().setValue(data.getValue());           //Colocando apenas a data atual para ambos
            if (this.dao.insertProduto(produto)) {
                return true;
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return false;
    }

//    public List<ProdutosDto> getListProdutos() throws Exception {
//        return this.dao.getListProdutos().stream().map(ProdutosDto::new).toList();
//    }
}
