package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.relatorios;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.LogOperacoes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogOperacoesDao {
    List<LogOperacoes> getLogOperacoes(Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim, Integer codigoOperacao) throws Exception;
}
