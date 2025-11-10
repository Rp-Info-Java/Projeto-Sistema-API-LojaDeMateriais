package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.municipios;

import br.framework.classes.DataBase.*;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Municipios;

import java.sql.ResultSet;
import java.util.List;

public class MunicipiosDaoImp extends Repository implements MunicipiosDao {

    public MunicipiosDaoImp(IConnection connection) {
        super(connection, MunicipiosDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    private Integer getNextCode() throws Exception {
        Integer codigo = 0;
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("max(muni_codigo) + 1 as muni_codigo")
                .from(Municipios.class);

        try (ResultSet q = this.getConnection().queryFactory(sql)) {
            if (q.next()) {
                codigo = q.getInt("muni_codigo");

                if (codigo == 0) {
                    codigo++;
                }
            }
        }
        return codigo;
    }

    @Override
    public boolean insert(Municipios municipios) throws Exception {
        municipios.getCodigo().setValue(this.getNextCode());
        municipios.toInsert();
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(municipios);
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
    public boolean update(Municipios municipios) throws Exception {
        municipios.toUpdate("muni_codigo = " + municipios.getCodigo().getValue());
        Transaction transaction = null;
        try {
            transaction = this.getConnection().getTransaction();
            transaction.addEntity(municipios);
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
    public Municipios getMunicipio(Integer id) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Municipios.class)
                .where("muni_codigo", "=", id);
        List<Municipios> list = this.getManager().queryFactory(sql, Municipios.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Municipios> getListMunicipios() throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Municipios.class)
                .orderBy("muni_codigo");

        return this.getManager().queryFactory(sql.build(), Municipios.class);
    }

    @Override
    public List<Municipios> getListMunicipiosByUf(String uf) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")
                .from(Municipios.class)
                .where("muni_uf", "=", uf.toUpperCase())
                .orderBy("muni_codigo");

        return this.getManager().queryFactory(sql.build(), Municipios.class);
    }
}
