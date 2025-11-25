package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios;

import br.framework.classes.DataBase.QueryBuilder;
import br.framework.classes.DataBase.Repository;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.LogOperacoes;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.Date;
import java.util.List;

public class LogOperacoesDaoImp extends Repository implements LogOperacoesDao {

    public LogOperacoesDaoImp(IConnection connection) {
        super(connection, LogOperacoesDao.class);
        this.getManager().setProcessNullToDefaultValues(false);
    }

    @Override
    public List<LogOperacoes> getLogOperacoes(Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim) throws Exception {
        QueryBuilder sql = QueryBuilder.create(this.getConnection())
                .select("*")                                           //quando for fazer a pesquisa por campos específicos, sempre lembrar de por a vírgula após o ult. campo
                .from(LogOperacoes.class)
                .where("1", "=", 1);

        if(codigoDeUsuario != null){
            sql.and("log_usua_codigo", "=", codigoDeUsuario);
        }
        if(tipoOperacao != null){
            sql.and("log_descricao", " LIKE ", "%" + tipoOperacao.toLowerCase() + "%");
        }
        if(dataInicio != null){
            Date dataIni = DocumentoUtils.parseData(dataInicio);
            sql.and("log_data", "<=", dataIni);
        }
        if(dataFim != null){
            Date dataF = DocumentoUtils.parseData(dataFim);
            sql.and("log_data", "<=", dataF);
        }
        return this.getManager().queryFactory(sql.build(), LogOperacoes.class);
    }
}
