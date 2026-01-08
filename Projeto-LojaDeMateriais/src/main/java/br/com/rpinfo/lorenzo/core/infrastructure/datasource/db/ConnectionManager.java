package br.com.rpinfo.lorenzo.core.infrastructure.datasource.db;

import br.framework.classes.DataBase.DataBaseProperties;
import br.framework.classes.DataBase.PostgreSQL.PostgresConnection;
import br.framework.classes.helpers.Types;
import br.framework.interfaces.IConnection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import br.com.rpinfo.lorenzo.core.application.service.UsuariosService;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Usuarios;
import br.com.rpinfo.lorenzo.core.infrastructure.config.Configuration;

import java.sql.SQLException;

public class ConnectionManager {
    private final DataBaseProperties dbProps = new DataBaseProperties(Configuration.getInstance().getDataBase());
    private static volatile ConnectionManager instance;
    private HikariDataSource dataSource;
    @Getter
    @Setter
    private ConnectionEventHandler eventHandler;
    @Getter
    @Setter
    private Usuarios usuario;

    public ConnectionManager() throws SQLException {
        this.buildAttributes();
        this.eventHandler = new ConnectionEventHandler();
    }

    public static void build() throws SQLException {
        if (ConnectionManager.instance == null) {
            //locker.lock();
            try {
                if (ConnectionManager.instance == null) {
                    ConnectionManager.instance = new ConnectionManager();
                }
//                ConnectionManager.setApplicationName();
            } finally {
                //  locker.unlock();
            }
        }
    }

    public static IConnection newConnection() throws SQLException, ValidationException, Exception {
        IConnection connection = null;
        IConnection.ConnectionEvents eventHandler = new ConnectionEventHandler(new RestDbCommandListener());
        ConnectionManager manager = ConnectionManager.getInstance();
        connection = manager.factory(eventHandler);
        manager.setUsuario(new UsuariosService(connection).getUsuarioEntityById(Configuration.getInstance().getUsuarioCodigo()));
        return connection;
    }

    public static ConnectionManager getInstance() throws SQLException {
        if (ConnectionManager.instance == null) {
            //locker.lock();
            try {
                if (ConnectionManager.instance == null) {
                    ConnectionManager.instance = new ConnectionManager();
                }
            } finally {
                //  locker.unlock();
            }
        }
        return ConnectionManager.instance;
    }

    private IConnection buildConnectionInstance(DataBaseProperties properties, IConnection.ConnectionEvents eventHandler) {
        //locker.lock();
        IConnection result = null;
        eventHandler = eventHandler != null ? eventHandler : this.eventHandler;

        try{
            if (properties.getServerType() == Types.DataBase.Postgres) {
                PostgresConnection psConnection = new PostgresConnection(properties, null);
                psConnection.setEventHandler(eventHandler);
                result = psConnection;
            }
        }finally{}
        return result;
    }


    private IConnection factory(IConnection.ConnectionEvents eventHandler) throws SQLException, ValidationException {
        DataBaseProperties properties = this.dbProps;
        IConnection connection = this.buildConnectionInstance(properties, eventHandler);
        HikariDataSource dataSource = this.dataSource;
//        Integer awaiting = this.getAwaitingCount();
//        if (awaiting> largestAwaitingCount.get() && this.getIdleCount()==0) {
//            largestAwaitingCount.set(awaiting + 1);
//        }
        connection.getEventHandler().onOpen(connection);
        try {
            connection.setConnection(dataSource.getConnection());
        } catch (Exception e) {
//            ConnectionManager.removeConnection(connection);
            throw e;
        }
        return connection;
    }

    private void buildAttributes() throws SQLException {
//        locker.lock();
        DataBaseProperties properties;
        properties = this.dbProps;
        this.dataSource = this.getHikariDataSource(properties);
//        } finally {
//            locker.unlock();
//        }
    }

    public HikariDataSource getHikariDataSource(DataBaseProperties properties) throws SQLException {
        return new HikariDataSource(this.getConfig(properties));
    }

    public HikariConfig getConfig(DataBaseProperties properties) throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        Types.DataBase banco = properties.getServerType();
        String driver = "";
        String url = "";
        String sqlTest = "SELECT 1";
        if (banco == Types.DataBase.Postgres) {
            driver = "org.postgresql.Driver";
            url = "jdbc:postgresql://" + properties.getHostAdress();
            url += ":" + properties.getPort().toString() + "/";
            url += properties.getDataBaseName();
            //hikariConfig.setConnectionInitSql("SET application_name = '" + getApplicationName(properties.getUserName(), source, false) + "'");
        }  else if (banco == Types.DataBase.H2) {
            driver = "org.h2.Driver";
            url = "jdbc:h2:mem:" + properties.getDataBaseName();
            // Opções adicionais
            url += ";MODE=PostgreSQL"; // Modo de compatibilidade (opcional - pode ser ORACLE, MYSQL, etc.)
            // H2 não precisa de um teste específico
            sqlTest = "SELECT 1";
        }

        hikariConfig.setJdbcUrl(url);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setUsername(properties.getUserName());
        hikariConfig.setPassword(properties.getPassword());
        hikariConfig.setMaximumPoolSize(properties.getMaxConnections());
        hikariConfig.setConnectionTestQuery(sqlTest);
//        hikariConfig.setIdleTimeout(IDLE_TIMEOUT_MS);
//        hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_MS);
//        hikariConfig.setMaxLifetime(MAX_LIFETIME_MS);
        hikariConfig.setPoolName("Hikari-" + properties.getDataBaseName());
        hikariConfig.setMinimumIdle(properties.getMaxConnections()<100 ? properties.getMaxConnections() : Double.valueOf(properties.getMaxConnections() * 0.21).intValue());

        return hikariConfig;
    }
}