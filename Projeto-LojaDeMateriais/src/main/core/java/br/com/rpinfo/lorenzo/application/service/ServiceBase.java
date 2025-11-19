package main.core.java.br.com.rpinfo.lorenzo.application.service;

import br.framework.interfaces.IConnection;
import lombok.Getter;
import lombok.Setter;

public class ServiceBase {
    @Getter
    private IConnection connection;

    @Getter
    @Setter
    private String codigoConfig;

    public ServiceBase(IConnection conn){
        this.connection = conn;
        this.codigoConfig = "";
    }
}
