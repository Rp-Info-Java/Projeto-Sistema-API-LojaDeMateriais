package main.core.java.br.com.rpinfo.lorenzo.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;



public class Decimal extends EntityFieldShort implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = -720035125290742976L;
  

	public Decimal() {
		this(true);
	}

	public Decimal(Boolean autoEnabled) {
        super(autoEnabled);
		super.setValue(0.00);
        super.setEnabled(false);
		this.setDataType(FieldType.BigDecimal);
	}

	@Override
	public Double getValue() {
		return (Double) super.getValue();
	}

	@Override
	public void setValue(Object value) {	
		this.setHasModification(Boolean.TRUE);
		Double doubleValue = (Double) value;
		super.setValue(doubleValue);

	}
	
	public Decimal setValue(Double value) {
		Object ObjValue = value;
		this.setValue(ObjValue);
		return this;
	}

}
