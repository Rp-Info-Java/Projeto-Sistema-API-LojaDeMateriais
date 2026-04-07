package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.ProdutosMovimentacoes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RelatoriosDelphiDto extends BaseDto {
    private List<MovProdutosCabDto> relatorioMovimentacoesEntrada;
    private List<MovProdutosCabDto> relatorioMovimentacoesSaida;
    private List<MovProdutosCabDto> relatorioMovimentacoesCanceladas;
    private List<VendComissoesDto> relatorioMovimentacoesGeralVendedores;
    private List<PendFinDto> relatorioMovimentacoesPendFin;
    private List<ProdVendDto> relatorioMovimentacoesProdVend;
    private List<MovProdutosCabDto> relatorioMovimentacoesTransacao;
    private List<MovProdutosCabDto> relatorioMovimentacoesUnitVendedores;
    private List<ProdutosMovimentacoesDto> relatorioMovimentacoesProdutos;

    public RelatoriosDelphiDto() {
        super();
        this.relatorioMovimentacoesTransacao = new ArrayList<>(); //Devido a uma das implementações, é necessário inicializar esse relatório
        this.relatorioMovimentacoesUnitVendedores = new ArrayList<>();
    }
}
