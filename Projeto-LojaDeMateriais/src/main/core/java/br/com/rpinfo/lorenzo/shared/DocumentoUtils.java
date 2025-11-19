package main.core.java.br.com.rpinfo.lorenzo.shared;

public final class DocumentoUtils {
    private DocumentoUtils() {}

    public static boolean validarTipo(String tipo){
        if(("F").equals(tipo) || ("J").equals(tipo)){
            return true;
        }
        return false;
    }

    public static boolean validarSituacao(String situacao){
        if(("N").equals(situacao) || ("I").equals(situacao)){
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

    public static boolean validarCamposConfig(String campo){
        if(("S").equals(campo) || ("N").equals(campo)){
            return true;
        }
        return false;
    }

}
