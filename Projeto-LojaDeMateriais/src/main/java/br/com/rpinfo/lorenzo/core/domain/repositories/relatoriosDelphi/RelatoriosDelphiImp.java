package br.com.rpinfo.lorenzo.core.domain.repositories.relatoriosDelphi;

import br.com.rpinfo.lorenzo.core.domain.model.entity.Cliente;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Fornecedores;
import br.com.rpinfo.lorenzo.core.domain.model.entity.MovProdutosC;
import br.framework.classes.DataBase.QueryBuilder;
import br.framework.classes.DataBase.Repository;
import br.framework.interfaces.IConnection;

import java.util.Date;
import java.util.List;

public class RelatoriosDelphiImp extends Repository implements RelatoriosDelphiDao {
    public RelatoriosDelphiImp(IConnection connection){
        super(connection, RelatoriosDelphiDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public List<MovProdutosC> getEntradas(Date dataIni, Date dataFim) throws Exception{
        try{
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Fornecedores.class, "mvpc_codentidade = forn_codigo")
                    .and("mvpc_status", "=", "N")
                    .and("mvpc_es", "=", "E")
                    .where("mvpc_datamvto").between(dataIni, dataFim)
                    .orderBy("mvpc_transacao");


            return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações de entrada na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<MovProdutosC> getSaidas(Date dataInicial, Date dataFinal) throws Exception{
        try{
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Cliente.class, "mvpc_codentidade = clie_codigo")
                    .and("mvpc_status", "=", "N")
                    .and("mvpc_es", "=", "S")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .orderBy("mvpc_transacao");


            return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações de saída na data especificada: " + e.getMessage());
        }
    }

    @Override
    public List<MovProdutosC> getCancelados(Date dataInicial, Date dataFinal) throws Exception{
        try{
            QueryBuilder sql = QueryBuilder.create(this.getConnection())
                    .select("*")
                    .from(MovProdutosC.class)
                    .innerJoin(Cliente.class, "mvpc_codentidade = clie_codigo")
                    .and("mvpc_status", "=", "C")
                    .innerJoin(Fornecedores.class, "mvpc_codentidade = forn_codigo")
                    .where("mvpc_datamvto").between(dataInicial, dataFinal)
                    .orderBy("mvpc_transacao");


            return this.getManager().queryFactory(sql.build(), MovProdutosC.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro buscando as movimentações canceladas na data especificada: " + e.getMessage());
        }
    }
}
