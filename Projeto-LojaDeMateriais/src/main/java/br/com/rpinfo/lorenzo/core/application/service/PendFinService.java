package br.com.rpinfo.lorenzo.core.application.service;

import br.com.rpinfo.lorenzo.core.application.dto.PagamentoDto;
import br.com.rpinfo.lorenzo.core.domain.exceptions.ValidationException;
import br.com.rpinfo.lorenzo.core.domain.model.entity.Pagamento;
import br.com.rpinfo.lorenzo.core.domain.model.entity.PendFin;
import br.com.rpinfo.lorenzo.core.domain.repositories.pendfin.PendFinDao;
import br.com.rpinfo.lorenzo.core.domain.repositories.pendfin.PendFinDaoImp;
import br.framework.interfaces.IConnection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PendFinService extends ServiceBase {

    private PendFinDao dao;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int CASAS_DECIMAIS = 2;

    public PendFinService(IConnection connection) {
        super(connection);
        this.dao = new PendFinDaoImp(connection);
    }

    public boolean adicionarPendFin(PagamentoDto pgtoDto) throws ValidationException {
        try {
            if (pgtoDto == null) {
                throw new ValidationException("Os dados de pagamento são nulos.");
            }
            Pagamento pgto = validaPagamento(pgtoDto);

            int totalParcelas = pgto.getDataVcto().size();

            for (int i = 0; i < totalParcelas; i++) {
                PendFin pfin = new PendFin();
                pfin.getStatus().setValue("N");

                if (("E").equals(pgtoDto.getFlagES())){
                    pfin.getCatentidade().setValue("F");
                    pfin.getTipo().setValue("P");
                    pfin.getOrigem().setValue("C");
                } else if (("S").equals(pgtoDto.getFlagES())){
                    pfin.getCatentidade().setValue("C");
                    pfin.getTipo().setValue("R");
                    pfin.getOrigem().setValue("V");
                }

                pfin.getTransacao().setValue(pgtoDto.getTransacao());
                pfin.getCodentidade().setValue(pgtoDto.getCodEntidade());
                pfin.getDocumento().setValue(pgtoDto.getConfig());

                // Dados calculados pelo validaPagamento
                pfin.getValor().setValue(Double.parseDouble(pgto.getValTotal().get(i)));
                pfin.getParcela().setValue(Double.parseDouble(pgto.getParcelas().get(i)));

                LocalDate localDate = LocalDate.parse(pgto.getDataVcto().get(i), DATE_FORMATTER);
                Date dataVcto = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                pfin.getDatavcto().setValue(dataVcto);

                if (!this.dao.insertPendFin(pfin)) {
                    throw new ValidationException("Erro ao inserir parcela " + (i + 1) + " da pendência.");
                }
            }
            return true;

        } catch (ValidationException e) {
            throw new ValidationException("Erro ao adicionar pfin: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar inserir a pfin: " + e.getMessage());
        }
    }

    private static Pagamento validaPagamento(PagamentoDto pgtoDto) {
        LocalDate pData = LocalDate.parse(pgtoDto.getDataMvto());
        Double pValor    = pgtoDto.getTotDcto();
        String pLista    = pgtoDto.getFormaPgto(); // "exemplo 30;60;"

        String[] prazosArray = pLista.split(";");
        int totalParcelas    = prazosArray.length;

        Pagamento recPgto = new Pagamento();

        BigDecimal valor   = BigDecimal.valueOf(pValor);
        BigDecimal total   = BigDecimal.valueOf(totalParcelas);
        BigDecimal parcela = valor.divide(total, CASAS_DECIMAIS, RoundingMode.HALF_UP);
        BigDecimal ultParc = valor.subtract(parcela.multiply(total)).add(parcela);

        for (int i = 0; i < totalParcelas; i++) {
            int diasPrazo      = Integer.parseInt(prazosArray[i].trim());
            LocalDate dataVcto = pData.plusDays(diasPrazo);

            recPgto.getDataVcto().add(dataVcto.format(DATE_FORMATTER));
            recPgto.getParcelas().add(String.valueOf(i + 1));

            if (i == totalParcelas - 1) {
                recPgto.getValTotal().add(ultParc.toPlainString());
            } else {
                recPgto.getValTotal().add(parcela.toPlainString());
            }
        }

        return recPgto;
    }
}
