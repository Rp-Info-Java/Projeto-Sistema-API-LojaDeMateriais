package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.application.dto.LogOperacoesDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatorios.LogOperacoesDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.relatorios.LogOperacoesDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;

import java.util.List;

public class LogOperacoesService extends ServiceBase {

    private LogOperacoesDao dao;

    public LogOperacoesService(IConnection connection) {
        super(connection);
        this.dao = new LogOperacoesDaoImp(connection);
    }

    public List<LogOperacoesDto> getListLogOperacoes(Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim, Integer codigoOperacao) throws Exception {
        try{
            DocumentoUtils.gravaLog(this.getConnection(), 70, "Consulta da lista de log de operações");
            return this.dao.getLogOperacoes(codigoDeUsuario, tipoOperacao, dataInicio, dataFim, codigoOperacao).stream().map(LogOperacoesDto::new).toList();
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao consultar o log de operações: " + e.getMessage());
        }
    }
}
