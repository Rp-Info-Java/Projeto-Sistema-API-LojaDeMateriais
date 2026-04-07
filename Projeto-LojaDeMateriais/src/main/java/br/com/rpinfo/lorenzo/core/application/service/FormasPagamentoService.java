package br.com.rpinfo.lorenzo.core.application.service;

import br.com.rpinfo.lorenzo.core.application.dto.FormasPagamentoDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.NullPointerException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.FormasPagamento;
import br.com.rpinfo.lorenzo.core.domain.repositories.formaspagamento.FormasPagamentoDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.formaspagamento.FormasPagamentoDaoImp;
import br.com.rpinfo.lorenzo.core.shared.DocumentoUtils;
import br.framework.interfaces.IConnection;

public class FormasPagamentoService extends ServiceBase{
    private FormasPagamentoDao dao;

    public FormasPagamentoService(IConnection connection){
        super(connection);
        this.dao = new FormasPagamentoDaoImp(connection);
    }

    public FormasPagamentoDto getFormasPagamentoById(Integer id) throws Exception {
        FormasPagamento fpgt = this.dao.getFormasPagamento(id);

        try {
            if (fpgt != null) {
                //DocumentoUtils.gravaLog(this.getConnection(), XX, "Consulta de um fpgt específico por ID");
                return fpgt.toDto();
            }
            return null;
        } catch (Exception e) {
            throw new NullPointerException("Erro ao buscar fpgt pelo ID: " + e.getMessage());
        }
    }

}
