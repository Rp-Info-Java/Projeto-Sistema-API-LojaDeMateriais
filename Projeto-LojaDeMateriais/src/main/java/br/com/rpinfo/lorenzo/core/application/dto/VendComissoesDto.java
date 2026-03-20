package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.VendComissoes;
import br.framework.classes.dto.annotations.Length;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendComissoesDto extends BaseDto {
    private Integer codigo;
    private String nome;
    private Double vendComissao;
    @Length(max=1)
    private String status;
    @Length(max=1)
    private String es;
    private Double soma;
    private Double comissao;

    public VendComissoesDto() { super(); }

    public VendComissoesDto(VendComissoes vendCom) {
        this.codigo = vendCom.getVend_codigo().getValue();
        this.nome = vendCom.getVend_nome().getValue();
        this.vendComissao = vendCom.getVend_comissao().getValue();
        this.status = vendCom.getStatus().getValue();
        this.es = vendCom.getEs().getValue();
        this.soma = vendCom.getSoma().getValue();
        this.comissao = vendCom.getComissaoCalculada().getValue();
    }

    public VendComissoes toEntity(){
        VendComissoes vendCom = new VendComissoes(false);
        vendCom.getVend_codigo().setValue(this.getCodigo());
        vendCom.getVend_nome().setValue(this.getNome());
        vendCom.getVend_comissao().setValue(this.getVendComissao());
        vendCom.getStatus().setValue(this.getStatus());
        vendCom.getEs().setValue(this.getEs());
        vendCom.getSoma().setValue(this.getSoma());
        vendCom.getComissaoCalculada().setValue(this.getComissao());
        return vendCom;
    }
}
