package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelatoriosDelphiDto extends BaseDto {
    private List<MovProdutosCabDto> relatorioMovimentacoesEntrada;
    private List<MovProdutosCabDto> relatorioMovimentacoesSaida;
    private List<MovProdutosCabDto> relatorioMovimentacoesCanceladas;

    public RelatoriosDelphiDto() {
        super();
    }
}
