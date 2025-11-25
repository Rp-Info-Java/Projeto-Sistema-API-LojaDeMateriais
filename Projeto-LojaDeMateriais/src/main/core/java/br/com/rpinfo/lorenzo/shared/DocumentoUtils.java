package main.core.java.br.com.rpinfo.lorenzo.shared;

import br.framework.classes.DataBase.Transaction;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.domain.model.entity.LogOperacoes;
import main.core.java.br.com.rpinfo.lorenzo.infrastructure.datasource.db.ConnectionManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    public static String formatarHorario(LocalTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return hora.format(formatter);
    }

    public static Date parseData(String dataComoString) {
        if (dataComoString == null || dataComoString.trim().isEmpty()) {
            return null;
        }

        //Define o formato da String de entrada
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate data = LocalDate.parse(dataComoString, formatter);

        //Pra funcionar a converesão, precisa de um ZoneId (Fuso Horário).
        return Date.from(data.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant());
    }

    public static void gravaLog(IConnection connection, Integer codigoLog, String descricao) {
        LogOperacoes log = new LogOperacoes();
        Transaction transaction = null;
        try {
            if (!logs(codigoLog)) {
                return;
            }
            log.getCodigo().setValue(codigoLog);
            log.getDescricao().setValue(descricao);
            log.getData().setValue(connection.getCurrentDate());
            log.getHora().setValue(formatarHorario(LocalTime.now()));
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
        logMap.put(10, "Gravação de configuração");  //Todas os logs relacionados a configuração serão entre 10 e 19
        logMap.put(11, "Edição nas configurações");  //Todas os logs relacionados a configuração serão entre 10 e 19
        logMap.put(12, "Consulta de configurações");  //Todas os logs relacionados a configuração serão entre 10 e 19

        logMap.put(20, "Gravação de cliente"); //Log clientes entre 20 e 29
        logMap.put(21, "Edição de dados de cliente"); //Log clientes entre 20 e 29
        logMap.put(22, "Consulta de cliente(s)"); //Log clientes entre 20 e 29

        logMap.put(30, "Gravação de fornecedor"); //Log fornecedores entre 30 e 39
        logMap.put(31, "Edição de dados de fornecedor"); //Log fornecedores entre 30 e 39
        logMap.put(32, "Consulta de fornecedor(es)"); //Log fornecedores entre 30 e 39

        logMap.put(40, "Gravação de produto"); //Log produtos entre 40 e 49
        logMap.put(41, "Edição de dados de produto"); //Log produtos entre 40 e 49
        logMap.put(42, "Consulta de produto(s)"); //Log produtos entre 40 e 49

        logMap.put(50, "Gravação de movimentação"); //Log Movimentações entre 50 e 59
        logMap.put(51, "Edição de dados de movimentação(ões)"); //Log Movimentações entre 50 e 59
        logMap.put(52, "Consulta de movimentação(ões)"); //Log Movimentações entre 50 e 59

        logMap.put(60, "Gravação de usuário"); //Log Usuários entre 60 e 69
        logMap.put(61, "Edição de dados de usuário"); //Log Usuários entre 60 e 69
        logMap.put(62, "Consulta de usuário(s)"); //Log Usuários entre 60 e 69

        logMap.put(70, "Consulta de logs de operações"); // Log de operações

        logMap.put(80, "Gravação de Município"); //Log Municípios entre 80 e 89
        logMap.put(81, "Edição de dados de Município(s)");
        logMap.put(82, "Consulta de Município(s)");

        logMap.put(90, "Gravação de Vendedor"); //Log Vendedores entre 90 e 99
        logMap.put(91, "Edição de dados de Vendedor");
        logMap.put(92, "Consulta de dados de Vendedor(es)");

        return logMap.containsKey(codigoLog);
    }

    private static String getLogDescription(Integer codigoLog) {
        Map<Integer, String> logMap2 = new HashMap<>();
        logMap2.put(1, "Gravação de configuração");
        logMap2.put(2, "Gravação de cliente");
        logMap2.put(3, "Gravação de fornecedor");
        logMap2.put(4, "Gravação de produto");
        logMap2.put(5, "Gravação de movimentação");
        logMap2.put(6, "Gravação de usuário");
        logMap2.put(7, "Gravação de tipo de documento");
        logMap2.put(8, "Gravação de tipo de movimentação");
        logMap2.put(9, "Gravação de tipo de produto");
        logMap2.put(10, "Gravação de tipo de situação");

        return logMap2.get(codigoLog);
    }
}
