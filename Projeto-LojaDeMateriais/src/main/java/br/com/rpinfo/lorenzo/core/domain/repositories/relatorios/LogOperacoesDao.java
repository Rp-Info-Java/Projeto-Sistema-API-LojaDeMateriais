package br.com.rpinfo.lorenzo.core.domain.repositories.relatorios;

import br.com.rpinfo.lorenzo.core.domain.model.entity.LogOperacoes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogOperacoesDao {
    List<LogOperacoes> getLogOperacoes(Integer codigoDeUsuario, String tipoOperacao, String dataInicio, String dataFim, Integer codigoOperacao) throws Exception;
}
