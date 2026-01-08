package br.com.rpinfo.lorenzo.core.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types;
import br.framework.classes.types.Currency;

import java.io.Serializable;
import java.math.BigDecimal;

public class DecimalFixo extends EntityFieldShort implements Serializable {

    private Currency currency;

    public DecimalFixo() {
        this(true);
    }

    public DecimalFixo(Boolean autoEnabled) {
        super(autoEnabled);
        this.currency = new Currency();
        this.setDataType(Types.FieldType.BigDecimal);
    }

    public DecimalFixo(double value) {
        this();
        this.setValue(value);
    }

    public DecimalFixo(BigDecimal value) {
        this(value.doubleValue());
    }

    public DecimalFixo(Currency value) {
        this(value.getValue());
    }

    public DecimalFixo(DecimalFixo value) {
        this(value.getValue());
    }

    public void setValue(double d) {
        this.currency.setValue(d);
    }

    public void setValue(Double d) {
        if (d!=null) {
            this.setValue(d.doubleValue());
        }
    }

    @Override
    public void setValue(Object value) {
        if (this.getAutoEnabled()) {
            super.setEnabled(true);
        }
        this.setHasModification(Boolean.TRUE);
        if (value!=null) {
            if (value instanceof Double doubleValue) {
                this.setValue(doubleValue);
            } else if (value instanceof BigDecimal bgDecValue) {
                this.setValue(bgDecValue);
            } else if (value instanceof Currency currency1) {
                this.setValue(currency1.getValue());
            } else if (value instanceof DecimalFixo decimalFixo) {
                this.setValue(decimalFixo);
            } else {
                this.setValue((double) value);
            }
        } else {
            this.currency.setValue(0);
        }
    }

    public DecimalFixo setValue(BigDecimal value) {
        this.setValue(value.doubleValue());
        return this;
    }

    public DecimalFixo setValue(DecimalFixo value) {
        return this.setValue(value.getValue());
    }

    @Override
    public BigDecimal getValue() {
        return this.currency.getValue();
    }

    public Currency add(double d) {
        return this.currency.add(d);
    }

    public Currency add(Currency c) {
        return this.currency.add(c);
    }

    public Currency subtract(double d) {
        return this.currency.subtract(d);
    }

    public Currency subtract(Currency c) {
        return this.currency.subtract(c);
    }

    public Currency multiply(double d) {
        return this.currency.multiply(d);
    }

    public Currency multiply(Currency c) {
        return this.currency.multiply(c);
    }

    public Currency divide(double d) {
        return this.currency.divide(d);
    }

    public Currency divide(Currency c) {
        return this.currency.divide(c);
    }

    public int compareTo(Currency c) {
        return this.currency.compareTo(c);
    }

    public String toString() {
        return this.currency.toString();
    }

    public Currency clone() {
        return this.currency.clone();
    }

    public void setDecimals(byte decimals) {
        this.currency.setDecimals(decimals);
    }
}
