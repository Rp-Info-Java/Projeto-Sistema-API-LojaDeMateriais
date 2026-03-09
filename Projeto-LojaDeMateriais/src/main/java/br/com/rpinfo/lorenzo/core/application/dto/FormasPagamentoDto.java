package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.FormasPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description =  "Dados das formas de pagamento")
@Getter
@Setter
public class FormasPagamentoDto extends BaseDto {
    private String codigo;
    private String descricao;
    private String prazos;

    public FormasPagamentoDto() { super(); }

    public FormasPagamentoDto(FormasPagamento fpgto){
        this.codigo = fpgto.getCodigo().getValue();
        this.descricao = fpgto.getDescricao().getValue();
        this.prazos = fpgto.getPrazos().getValue();
    }

    public FormasPagamento toEntity(){
        FormasPagamento fpgto = new FormasPagamento(false);
        fpgto.getCodigo().setValue(this.getCodigo());
        fpgto.getDescricao().setValue(this.getDescricao());
        fpgto.getPrazos().setValue(this.getPrazos());
        return fpgto;
    }
}
