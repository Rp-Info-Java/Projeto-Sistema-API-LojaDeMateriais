package br.com.rpinfo.lorenzo.core.domain.repositories.pendfin;

import br.com.rpinfo.lorenzo.core.domain.model.entity.PendFin;

public interface PendFinDao {
    boolean insertPendFin (PendFin pfin) throws Exception;
}
