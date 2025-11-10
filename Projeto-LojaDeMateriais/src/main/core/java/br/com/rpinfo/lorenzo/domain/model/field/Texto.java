package main.core.java.br.com.rpinfo.lorenzo.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;



public class Texto extends EntityFieldShort implements Serializable{

	/**
   * 
   */
  private static final long serialVersionUID = 2080140130533091979L;

	public Texto() {
		this(true);
	}

	public Texto(Boolean autoEnabled) {
        super(autoEnabled);
		super.setValue("");
        super.setEnabled(false);
		this.setDataType(FieldType.Memo);
	}

	@Override
	public String getValue() {
		return (String) super.getValue();
	}

	@Override
	public void setValue(Object value) {
		this.setHasModification(Boolean.TRUE);
		String strValue = (String) value;
		super.setValue(strValue);

	}
	
	public Texto setValue(String value) {
		Object ObjValue = value;
		this.setValue(ObjValue);
		return this;
	}

}
