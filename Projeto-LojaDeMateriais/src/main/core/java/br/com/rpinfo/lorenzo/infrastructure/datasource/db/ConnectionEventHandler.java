package main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db;

import br.framework.classes.helpers.Log;
import br.framework.interfaces.IConnection;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ConnectionEventHandler implements IConnection.ConnectionEvents {
    public ConnectionEventHandler(RestDbCommandListener restDbCommandListener) {
    }

    public ConnectionEventHandler(){ super(); }

    @Override
    public void onClose(IConnection connection) {

    }

    @Override
    public void onOpen(IConnection connection) {

    }

    @Override
    public void onQueryFactory(IConnection connection, String sql) {

    }

    @Override
    public void onQuery(IConnection connection, String sql) {

    }

    @Override
    public void onExecuteCommand(IConnection connection, String command, Long totalTime, Long dbTime) throws Exception {

    }
//    private final List<DbCommandListener> commandListeners;
//
//    public ConnectionEventHandler(DbCommandListener... commandListeners) {
//        this.commandListeners = List.of(commandListeners);
//    }
//
//    public ConnectionEventHandler(List<DbCommandListener> commandListeners) {
//        this.commandListeners = commandListeners;
//    }
//
//    @Override
//    public void onClose(IConnection connection) {
//        ConnectionManager.removeConnection(connection);
//        if (ApplicationContext.getDebugConnections()) {
//            try {
//                throw new Exception("Fechando conexão");
//            } catch (Exception e) {
//                String stack = FlexFunctions.getStackByException(e);
//                connection.setStackClose(stack);
//            }
//            Log.info("Fechando a conexão '" + connection.getName() + "'");
//            Log.info("Stack: '" + connection.getStackClose() + "'");
//        }
//    }
//
//    @Override
//    public void onOpen(IConnection connection) {
//        ConnectionManager.putConnection(connection);
//        if (ApplicationContext.getDebugConnections()) {
//            try {
//                throw new Exception("Abrindo conexão");
//            } catch (Exception e) {
//                String stack = FlexFunctions.getStackByException(e);
//                connection.setStackCriacao(stack);
//            }
//            Log.info("Abrindo a conexão '" + connection.getName() + "'");
//            Log.info("Stack: '" + connection.getStackCriacao() + "'");
//        }
//    }
//
//    @Override
//    public void onQueryFactory(IConnection connection, String sql) {
//        if (ApplicationContext.debug) {
//            Log.info("onQueryFactory: '" + sql + "'");
//        }
//    }
//
//    @Override
//    public void onQuery(IConnection connection, String sql) {
//        if (ApplicationContext.debug) {
//            Log.info("onQuery: '" + sql + "'");
//        }
//    }
//
//    @Override
//    public void onExecuteCommand(IConnection connection, String command, Long totalTime, Long dbTime) {
//        DatabaseCommand dbCommand = new DatabaseCommand(command, totalTime, dbTime, Date.from(Instant.now()));
//        for (DbCommandListener listener : commandListeners) {
//            if (listener.isActive()) {
//                listener.receive(connection, dbCommand);
//            }
//        }
}

