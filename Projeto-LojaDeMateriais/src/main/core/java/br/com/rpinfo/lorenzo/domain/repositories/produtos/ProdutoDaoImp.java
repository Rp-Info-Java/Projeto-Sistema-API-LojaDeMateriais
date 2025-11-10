package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.produtos;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Cliente;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Produtos;

import java.sql.ResultSet;
import java.util.List;

public class ProdutoDaoImp extends Repository implements ProdutoDao {

    public ProdutoDaoImp(IConnection connection) {
        super(connection, ProdutoDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private Integer getNextCode() throws Exception {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(prod_codigo) + 1 as prod_codigo")
                .from(Produtos.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("prod_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insertProduto(Produtos produto) throws Exception {
        produto.getCodigo().setValue(this.getNextCode());
        produto.toInsert();
        Transaction transaction = null;
        try{
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(produto);
            if(transaction.commit()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            try{
                if(transaction != null){
                    transaction.rollback();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Produtos produto) throws Exception {
        return false;
    }

    @Override
    public Produtos getProduto(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Produtos> getListProdutos() throws Exception {
        return List.of();
    }
}
