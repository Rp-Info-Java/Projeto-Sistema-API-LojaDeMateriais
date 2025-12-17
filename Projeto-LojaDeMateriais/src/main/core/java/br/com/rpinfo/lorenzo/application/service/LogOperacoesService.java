package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.LogOperacoesDto;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.NullPointerException;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios.LogOperacoesDao;
import main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios.LogOperacoesDaoImp;
import main.core.java.br.com.rpinfo.lorenzo.shared.DocumentoUtils;

import java.util.List;

public class LogOperacoesService extends ServiceBase {

    private LogOperacoesDao dao;

    public LogOperacoesService(IConnection connection) {
        super(connection);
        this.dao = new LogOperacoesDaoImp(connection);
    }

    public List<LogOperacoesDto> getListLogOperacoes(Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim) throws Exception {
        try{
            DocumentoUtils.gravaLog(this.getConnection(), 70, "Consulta de log de operações");
            return this.dao.getLogOperacoes(codigoDeUsuario, tipoOperacao, dataInicio, dataFim).stream().map(LogOperacoesDto::new).toList();
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro ao consultar o log de operações: " + e.getMessage());
        }
    }
}
