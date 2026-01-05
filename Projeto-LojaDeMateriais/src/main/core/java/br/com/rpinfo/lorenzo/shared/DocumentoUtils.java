package main.core.java.br.com.rpinfo.lorenzo.shared;

import br.framework.classes.DataBase.Transaction;
import br.framework.interfaces.IConnection;
import main.core.java.br.com.rpinfo.lorenzo.application.dto.ConfiguracoesDto;
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
    private static final Map<Integer, String> logMap = new HashMap<>();
    private DocumentoUtils() {
    }

    public static boolean validarTipo(String tipo) {
        return ("F").equals(tipo) || ("J").equals(tipo);
    }

    public static boolean validarSituacao(String situacao) {
        return ("N").equals(situacao) || ("I").equals(situacao);
    }

    public static boolean validarTamanhoCpfCnpj(String cpfCnpj) {
        return cpfCnpj.length() == 11 || cpfCnpj.length() == 14;
    }

    public static boolean validarCamposConfig(String campo) {
        return ("S").equals(campo) || ("N").equals(campo);
    }

    public static String formatarHorario(LocalTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return hora.format(formatter);
    }

    public static boolean validarStatusMovimento(ConfiguracoesDto configDto, String status) {
        if(configDto != null){
            if(("N").equals(status)){
                return true;
            }else if(("C").equals(status)){
                if(("N").equals(configDto.getValidaSaidas())){
                    return true;
                }else if(("S").equals(configDto.getValidaSaidas())){
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean validarImpedimento(ConfiguracoesDto configDto, String situacao){
        if(configDto != null){
            if(("N").equals(configDto.getValidaFornecedor())){
                return true;
            }else if(("S").equals(configDto.getValidaFornecedor())){
                if(("N").equals(situacao)){
                    return true;
                }else if(("I").equals(situacao)){
                    return false;
                }
            }
        }
        return false;
    }

    public static Date parseData(String dataComoString) {
        if (dataComoString == null || dataComoString.trim().isEmpty()) {
            return null;
        }

        //Define o formato da String de entrada
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate data = LocalDate.parse(dataComoString, formatter);

        //Pra funcionar a conversão, precisa de um ZoneId (Fuso Horário).
        return Date.from(data.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant());
    }

    public static void gravaLog(IConnection connection, Integer codigoLog, String agrupamento) {
        LogOperacoes log = new LogOperacoes();
        Transaction transaction = null;
        try {
            if (!logs(codigoLog)) {
                return;
            }
            log.getCodigo().setValue(codigoLog);
            log.getDescricao().setValue(getLogDescription(codigoLog));
            log.getAgrupamento().setValue(agrupamento);
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

    // Não há problema em posicionar o bloco static abaixo de outras funções, visto que nenhum dos dados abaixo
    // será alterado durante a execução do código
    static {
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

        logMap.put(70, "Consulta de logs de operações"); // Logs do relatório 70 a 79
        logMap.put(71, "Consulta de entradas nas movimentações");
        logMap.put(72, "Consulta de saídas nas movimentações");
        logMap.put(73, "Consulta de movimentações canceladas");
        logMap.put(74, "Consulta de comissões dos vendedores");

        logMap.put(80, "Gravação de Município"); //Log Municípios entre 80 e 89
        logMap.put(81, "Edição de dados de Município(s)");
        logMap.put(82, "Consulta de Município(s)");

        logMap.put(90, "Gravação de Vendedor"); //Log Vendedores entre 90 e 99
        logMap.put(91, "Edição de dados de Vendedor");
        logMap.put(92, "Consulta de dados de Vendedor(es)");
    }

    public static boolean logs(Integer codigoLog){
        return logMap.containsKey(codigoLog);
    }

    public static String getLogDescription(Integer codigoLog){
        return logMap.get(codigoLog);
    }
}
