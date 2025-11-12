package main.core.java.br.com.rpinfo.lorenzo.shared;

public final class DocumentoUtils {
    private DocumentoUtils() {}

    public static boolean validarTipo(String tipo){
        if(tipo.equals("F") || tipo.equals("J")){
            return true;
        }
        return false;
    }

    public static boolean validarSituacao(String situacao){
        if(situacao.equals("N") || situacao.equals("I")){
            return true;
        }
        return false;
    }

    public static boolean validarTamanhoCpfCnpj(String cpfCnpj){
        if(cpfCnpj.length() == 11 || cpfCnpj.length() == 14){
            return true;
        }
        return false;
    }
}
