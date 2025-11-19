package main.core.java.br.com.rpinfo.lorenzo.domain.repositories.configuracoes;

import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.Configuracoes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfiguracoesDao {

    boolean insertConfiguracao (Configuracoes configuracoes) throws Exception;

    List<Configuracoes> getListConfiguracoes() throws Exception;

    Configuracoes getConfiguracao(Integer id) throws Exception;

    boolean updateConfiguracoes(Configuracoes configuracoes) throws Exception;
}
