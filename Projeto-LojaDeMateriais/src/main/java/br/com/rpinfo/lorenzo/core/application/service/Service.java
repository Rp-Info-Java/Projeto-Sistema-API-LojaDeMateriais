package br.com.rpinfo.lorenzo.core.application.service;

import br.framework.classes.helpers.Types;
import br.framework.interfaces.IConnection;
import br.com.rpinfo.lorenzo.core.infrastructure.config.Configuration;

public class Service extends ServiceBase{

    public Service() {
        super(null);
    }

    public Service(IConnection conn) {
        super(conn);
//        if (Configuration.getInstance().getReturnSql()) {
//            this.getConnection().setMode(Types.ScriptMode.LogAndToDataBase);
//        }
    }
}
