package main.core.java.br.com.rpinfo.lorenzo.infrastructure.config;

import br.framework.classes.DataBase.DataBaseProperties;
import lombok.Getter;
import lombok.Setter;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;

import java.io.IOException;

@Getter
@Setter
public class Configuration {
    public static final String version = "1.0.0.001";
    private static Configuration instance;
    private String host;
    private Integer httpPort;
    private Integer httpsPort;
    private String path;
    private DataBaseProperties dataBase;

    public Configuration() {
        this.dataBase = new DataBaseProperties("", "", "", "");
        this.dataBase.setMaxConnections(10);
        this.path = null;
        this.httpPort = null;
        this.httpsPort = null;
    }

    public static void build(String path) throws ValidationException {
        if (Configuration.instance == null) {
            //lock.lock();
            try {
                if (Configuration.instance == null) {
                    Configuration.instance = new Configuration();
                    Configuration.instance.path = path;
                    ConfigManager manager = new ConfigManager();
                    try {
                        manager.loadConfig(Configuration.instance);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                //lock.unlock();
            }
        }
    }

    public static void build() throws ValidationException {
        Configuration.build(null);
    }

    public static Configuration getInstance(){
        if (Configuration.instance == null) {
            try {
                Configuration.build();
            } catch (ValidationException e) {
//                Service.logException(e);
            }
        }
        return Configuration.instance;
    }
}
