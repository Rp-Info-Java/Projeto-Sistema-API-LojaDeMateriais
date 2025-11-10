package main.core.java.br.com.rpinfo.lorenzo.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;



public class Binario extends EntityFieldShort implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = -2156178518936620393L;


    public Binario() {
        this(true);
    }

    public Binario(Boolean autoEnabled) {
        super(autoEnabled);
        super.setValue(new Byte[0]);
        super.setEnabled(false);
        this.setDataType(FieldType.Blob);
    }

	@Override
	public Byte[] getValue() {		
		return (Byte[]) super.getValue();
	}

	public void setValue(Byte[] value) {
		Object ObjValue = value; 
		this.setValue(ObjValue);
	}
	
	@Override
	public void setValue(Object value) {
		this.setHasModification(Boolean.TRUE);
		byte[] byteValue = (byte[]) value;
		super.setValue(byteValue);
	}

	public Binario setValue(byte[] byteValue) {
		this.setHasModification(Boolean.TRUE);
		super.setValue(byteValue);
		return this;
	}

}
