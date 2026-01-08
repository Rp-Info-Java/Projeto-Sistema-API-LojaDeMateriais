package br.com.rpinfo.lorenzo.core.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;
import java.util.Date;


public class DataHora extends EntityFieldShort implements Serializable {

	/**
   *
   */
  private static final long serialVersionUID = 3277102956712145190L;


	public DataHora() {
		this(true);
	}

	public DataHora(Boolean autoEnabled) {
		super(autoEnabled);
		super.setValue(null);
        super.setEnabled(false);
		this.setDataType(FieldType.DateTime);
	}

	@Override
	public Date getValue() {
		return (Date) super.getValue();
	}

	public DataHora setValue(Date value) {
		Object ObjValue = value;
        this.setValue(ObjValue);
		return this;
	}
	
	@Override
	public void setValue(Object value) {
        if (value != null) {
            if (java.sql.Date.class == value.getClass()) {
                java.sql.Date sqlDate = (java.sql.Date) value;
                if (sqlDate != null) {
                    value = new Date(sqlDate.getTime());
                }
            }
        }
        this.setHasModification(Boolean.TRUE);
		Date dateValue = (Date) value;
		super.setValue(dateValue);
	}

}
