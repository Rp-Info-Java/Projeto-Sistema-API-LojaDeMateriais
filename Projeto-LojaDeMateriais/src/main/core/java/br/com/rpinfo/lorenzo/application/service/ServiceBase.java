package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import lombok.Getter;

public class ServiceBase {
    @Getter
    private IConnection connection;

    public ServiceBase(IConnection conn){
        this.connection = conn;
    }
}
