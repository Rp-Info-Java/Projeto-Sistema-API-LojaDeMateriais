package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.configuracoes;

import br.framework.classes.DataBase.QueryBuilder;
import br.framework.classes.DataBase.Repository;
import br.framework.classes.DataBase.Transaction;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Configuracoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConfiguracoesDaoImp extends Repository implements ConfiguracoesDao{

    public ConfiguracoesDaoImp(IConnection connection){
        super(connection, ConfiguracoesDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private Integer getNextCode() throws SQLException {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(conf_codigo) + 1 as conf_codigo")
                .from(Configuracoes.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("conf_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insertConfiguracao(Configuracoes configuracoes) throws SQLException {
        configuracoes.getCodigo().setValue(this.getNextCode());
        configuracoes.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(configuracoes);
            if (transaction.commit()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Configuracoes> getListConfiguracoes() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")                                           //quando for fazer a pesquisa por campos específicos, sempre lembrar de por a vírgula após o ult. campo
                .from(Configuracoes.class)
                .orderBy("conf_codigo");

        return this.getManager().queryFactory(sql.build(), Configuracoes.class);
    }

    @Override
    public Configuracoes getConfiguracao(Integer id) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Configuracoes.class)
                .where("conf_codigo", "=", id);

        List<Configuracoes> lista = this.getManager().queryFactory(sql, Configuracoes.class);

        if(!lista.isEmpty()){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public boolean updateConfiguracoes(Configuracoes config){
        config.toUpdate("conf_codigo = " + config.getCodigo().getValue());
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(config);
            if (transaction.commit()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
}