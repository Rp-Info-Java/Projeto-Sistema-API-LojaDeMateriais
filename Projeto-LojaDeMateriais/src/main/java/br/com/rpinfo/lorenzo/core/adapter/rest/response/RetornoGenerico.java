package br.com.rpinfo.lorenzo.core.adapter.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class RetornoGenerico {
    private Boolean hasError;
    private HashMap<String, String> campos;
    private List<String> erros;

    public RetornoGenerico(String id) {
        this.erros = new ArrayList<>();
        this.campos = new HashMap<>();
        this.addCampo("id", id);
    }

    public void addCampo(String nameField, String value) {
        this.campos.put(nameField, value);
    }

    public void addAllErro(List<String> erros) {
        this.erros.addAll(erros);
    }

    public void addErro(String erro) {
        this.erros.add(erro);
    }

    public void setStatusSucess(Boolean sucess) {
        this.addCampo("status", sucess ? "Ok" : "Erro");
        this.hasError = !sucess;
    }
}
