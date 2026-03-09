package br.com.rpinfo.lorenzo.core.domain.repositories.formaspagamento;

import br.com.rpinfo.lorenzo.core.domain.model.entity.FormasPagamento;
import org.springframework.stereotype.Repository;

@Repository
public interface FormasPagamentoDao{
    FormasPagamento getFormasPagamento(String codigo) throws Exception;
}
