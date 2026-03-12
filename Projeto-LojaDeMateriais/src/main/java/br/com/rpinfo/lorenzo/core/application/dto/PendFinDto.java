package br.com.rpinfo.lorenzo.core.application.dto;

import br.com.rpinfo.lorenzo.core.adapter.rest.response.BaseDto;
import br.com.rpinfo.lorenzo.core.domain.model.entity.PendFin;
import br.framework.classes.dto.annotations.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PendFinDto extends BaseDto {
    private String transacao;
    @Length(max = 1)
    private String status;
    private String catentidade;
    private Integer codEntidade;
    private String tipo;
    private String documento;
    private Double valor;
    private Double parcela;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date datavcto;
    private String origem;

    public PendFinDto(){ super(); }

    public PendFinDto(PendFin pfin){
        this.transacao = pfin.getTransacao().getValue();
        this.status = pfin.getStatus().getValue();
        this.catentidade = pfin.getCatentidade().getValue();
        this.codEntidade = pfin.getCodentidade().getValue();
        this.tipo = pfin.getTipo().getValue();
        this.documento = pfin.getDocumento().getValue();
        this.valor = pfin.getValor().getValue();
        this.parcela = pfin.getParcela().getValue();
        this.datavcto = pfin.getDatavcto().getValue();
        this.origem = pfin.getOrigem().getValue();
    }

    public PendFin toEntity(){
        PendFin pfin = new PendFin(false);
        pfin.getTransacao().setValue(this.getTransacao());
        pfin.getStatus().setValue(this.getStatus());
        pfin.getCatentidade().setValue(this.getCatentidade());
        pfin.getCodentidade().setValue(this.getCodEntidade());
        pfin.getTipo().setValue(this.getTipo());
        pfin.getDocumento().setValue(this.getDocumento());
        pfin.getValor().setValue(this.getValor());
        pfin.getParcela().setValue(this.getParcela());
        pfin.getDatavcto().setValue(this.getDatavcto());
        pfin.getOrigem().setValue(this.getOrigem());
        return pfin;
    }
}
