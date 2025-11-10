package main.core.java.br.com.rpinfo.lorenzo.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types;

import java.io.Serializable;

public class Extra extends EntityFieldShort implements Serializable {

    public Extra() {
        this(true);
    }

    public Extra(Boolean autoEnabled) {
        super(autoEnabled);
        this.setDataType(Types.FieldType.Unknown);
    }

    @Override
    public Object getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Object value) {
        this.setHasModification(Boolean.TRUE);
        super.setValue(value);
    }

    public Extra setValue(String value) {
        Object ObjValue = value;
        this.setValue(ObjValue);
        return this;
    }

    public Extra setValue(Integer value) {
        Object ObjValue = value;
        this.setValue(ObjValue);
        return this;
    }

    public Extra setValue(Double value) {
        Object ObjValue = value;
        this.setValue(ObjValue);
        return this;
    }

    public Extra setValue(Long value) {
        Object ObjValue = value;
        this.setValue(ObjValue);
        return this;
    }
}
