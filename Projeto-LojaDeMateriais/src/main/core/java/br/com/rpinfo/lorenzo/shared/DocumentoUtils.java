package main.core.java.br.com.rpinfo.lorenzo.shared;

import br.framework.classes.DataBase.Transaction;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.exceptions.ValidationException;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.LogOperacoes;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class DocumentoUtils {
    private DocumentoUtils() {
    }

    public static boolean validarTipo(String tipo) {
        if (("F").equals(tipo) || ("J").equals(tipo)) {
            return true;
        }
        return false;
    }

    public static boolean validarSituacao(String situacao) {
        if (("N").equals(situacao) || ("I").equals(situacao)) {
            return true;
        }
        return false;
    }

    public static boolean validarTamanhoCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() == 11 || cpfCnpj.length() == 14) {
            return true;
        }
        return false;
    }

    public static boolean validarCamposConfig(String campo) {
        if (("S").equals(campo) || ("N").equals(campo)) {
            return true;
        }
        return false;
    }

    public static void gravaLog(IConnection connection, Integer codigoLog, String descricao) {
        LogOperacoes log = new LogOperacoes();
        Transaction transaction = null;
        try {
            if(!logs(codigoLog)){
                return;
            }
            log.getCodigo().setValue(codigoLog);
            log.getDescricao().setValue(descricao);
            log.getData().setValue(connection.getCurrentDate());
            log.getHora().setValue(connection.getCurrentTime());
            log.getUsua_codigo().setValue(ConnectionManager.getInstance().getUsuario().getCodigo().getValue());
            log.toInsert();

            transaction = connection.getTransaction();
            transaction.addEntity(log);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private static boolean logs(Integer codigoLog) {
        Map<Integer, String> logMap = new HashMap<>();
        logMap.put(1, "Gravação de configuração");
        logMap.put(2, "Gravação de cliente");
        logMap.put(3, "Gravação de fornecedor");
        logMap.put(4, "Gravação de produto");
        logMap.put(5, "Gravação de movimentação");
        logMap.put(6, "Gravação de usuário");
        logMap.put(7, "Gravação de tipo de documento");
        logMap.put(8, "Gravação de tipo de movimentação");
        logMap.put(9, "Gravação de tipo de produto");
        logMap.put(10, "Gravação de tipo de situação");

        return logMap.containsKey(codigoLog);
    }
}
