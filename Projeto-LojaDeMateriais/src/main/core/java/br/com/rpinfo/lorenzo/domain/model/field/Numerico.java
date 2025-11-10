package main.core.java.br.com.rpinfo.lorenzo.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;



public class Numerico extends EntityFieldShort implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = 5237978494722523252L;

	public Numerico() {
		this(true);
	}

	public Numerico(Boolean autoEnabled) {
        super(autoEnabled);
		super.setValue(0);
        super.setEnabled(false);
		this.setDataType(FieldType.Integer);
	}

	@Override
	public Integer getValue() {
		return (Integer) super.getValue();
	}

	@Override
	public void setValue(Object value) {
		this.setHasModification(Boolean.TRUE);
		Integer intValue = (Integer) value;
		super.setValue(intValue);
	}
	
	public Numerico setValue(Integer value) {
		Object ObjValue = value;
		this.setValue(ObjValue);
		return this;
	}

}
