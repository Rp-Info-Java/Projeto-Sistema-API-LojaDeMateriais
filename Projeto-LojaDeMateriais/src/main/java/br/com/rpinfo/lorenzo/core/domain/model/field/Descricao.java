package br.com.rpinfo.lorenzo.core.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;



public class Descricao extends EntityFieldShort implements Serializable{

	/**
   * 
   */
  private static final long serialVersionUID = -7918689470856419413L;

    public Descricao() {
        this(true);
    }

    public Descricao(Boolean autoEnabled) {
        super(autoEnabled);
        try {
            super.setValue("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.setEnabled(false);
        this.setDataType(FieldType.String);
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
	
	
	public Descricao setValue(String value) {
		Object ObjValue = value;
		this.setValue(ObjValue);
		return this;
	}
	
	
	@Override
  public boolean equals(Object obj) {
    Boolean isEquals = Boolean.FALSE;  
		if (obj == this) {
      isEquals = Boolean.TRUE;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
    	isEquals = Boolean.FALSE;
    }
    String Value = ((Descricao)obj).getValue().toString().trim();
    String thisValue = this.getValue().toString().trim();
    if (Value.equals(thisValue)) {
    	isEquals = Boolean.TRUE;
    }     
    return isEquals; 
  }

}
