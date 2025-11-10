package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.classes.helpers.Types;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.config.Configuration;

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
