package br.com.rpinfo.lorenzo.core.infrastructure.config;

import br.framework.classes.DataBase.DataBaseProperties;
import br.framework.classes.helpers.PropertyFile;
import br.framework.classes.helpers.Types;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    @Getter @Setter
    private String filePath;
    private PropertyFile propertyFile;
    private Boolean hasEnv = false;

    public ConfigManager() {
        super();
        try {
            this.setFilePath(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(Configuration configuration) throws IOException, ValidationException{
        String path = this.getFilePath() + "/config.properties";
        if (configuration.getPath() != null && !Strings.isNotEmpty(configuration.getPath())) {
            path = configuration.getPath();
        }
        this.propertyFile = new PropertyFile(path);
        if (new File(path).exists()) {
            this.propertyFile.load();
        }
        hasEnv = System.getenv("DB_HOST")!=null;
//        this.loadConfigDefault(configuration);
        DataBaseProperties properties = configuration.getDataBase();
        this.loadDataBase(properties);
        this.loadConfigGeral(configuration);
        configuration.setDataBase(properties);
    }


    private void loadDataBase(DataBaseProperties properties) throws ValidationException {
        String user = loadStringVariable("DB_USER", "db.user", true);
        properties.setUserName(user);

        String host = loadStringVariable("DB_HOST", "db.host", false);
        properties.setHostAdress(host);

        Integer port = loadIntegerVariable("DB_PORT", "porta", 0);
        properties.setPort(port);

        String dataBaseName = loadStringVariable("DB_NAME", "db.name", true);
        properties.setDataBaseName(dataBaseName);

        String dataBaseType = loadStringVariable("DB_TYPE", "db.type", true);
        Types.DataBase dbType = Types.DataBase.valueOf(dataBaseType);
        properties.setServerType(dbType);



        String password = loadStringVariable("DB_PASSWORD", "db.password", "");
        if (Strings.isNotEmpty(password)) {
//            if (user.equalsIgnoreCase("ERP")) {
//                password = "erp94587361";
////                if (this.hasPWL()) {
////                    password = this.tryLoadPWL();
////                }
//            }
        }
        if (Strings.isNotEmpty(password)) {
            //this.errorCount.add("Configuração 'DB_PASSWORD'/'db.password' obrigatória");
        }
        properties.setPassword(password);

        Integer maxConnections = loadIntegerVariable("DB_MAX_CONNECTIONS", "db.maxConnections", 5);
        if (maxConnections < 5) {
            maxConnections = 5;
        }

        properties.setMaxConnections(maxConnections);

        if (dbType == Types.DataBase.Postgres) {
            properties.setDriverName("org.postgresql.Driver");
        }else if (dbType == Types.DataBase.H2){
            properties.setDriverName("org.h2.Driver");
        } else {
            throw new ValidationException("Configuração 'DB_TYPE'/'db.type' obrigatória: ");
        }
    }

    private String loadStringVariable(String envPath, String propertyPath, boolean required) {
        return loadStringVariable(envPath, propertyPath, required, "");
    }

    private String loadStringVariable(String envPath, String propertyPath, boolean required, String defaultValue) {
        String value;
        if (!hasEnv) {
            value = this.propertyFile.getProperty(propertyPath);
        } else {
            value = System.getenv(envPath);
        }
        if (required) {
            if (value == null || Strings.isNotEmpty(value)) {
//                this.errorCount.add("Configuração '" + envPath + "'/'" + propertyPath + "' obrigatória");
            }
        } else if (value == null || !Strings.isNotEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }

    private Integer loadIntegerVariable(String envPath, String propertyPath, Integer defaultValue) {
        return this.loadIntegerVariable(envPath, propertyPath, defaultValue, 0, null);
    }

    private Integer loadIntegerVariable(String envPath, String propertyPath, Integer defaultValue, Integer minValue, Integer maxValue) {
        try {
            String defStrValue = null;
            if (defaultValue!= null) {
                defStrValue = defaultValue.toString();
            }
            String value = this.loadStringVariable(envPath, propertyPath, defStrValue);
            if (value != null && Strings.isNotEmpty(value)) {
                Integer retValue = Integer.valueOf(value);
                if (defaultValue == null) {
                    return retValue;
                }
                if (maxValue!=null) {
                    if (retValue > maxValue) {
                        return maxValue;
                    }
                }
                if (minValue != null && retValue<minValue) {
                    return minValue;
                }
                return retValue;
            }
        } catch (Exception e) {
           // this.errorCount.add("Configuração '" + envPath + "'/'" + propertyPath + "' aceita apenas números");
        }
        return defaultValue;
    }

    private String loadStringVariable(String envPath, String propertyPath, String defaultValue) {
        return loadStringVariable(envPath, propertyPath, false, defaultValue);
    }

    private void loadConfigGeral(Configuration configuration) throws ValidationException {
        Integer user = loadIntegerVariable("USUARIO_CODIGO", "usuario.codigo", 0);
        configuration.setUsuarioCodigo(user);
    }
}
