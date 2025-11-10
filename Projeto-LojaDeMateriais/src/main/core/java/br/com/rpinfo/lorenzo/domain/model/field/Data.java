package main.core.java.br.com.rpinfo.lorenzo.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.StringFunctions;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;
import java.util.Date;


public class Data extends EntityFieldShort implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = 3277102956712145190L;	
  

	public Data() {
		this(true);
	}

    public Data(Boolean autoEnabled) {
		super(autoEnabled);
		super.setValue(null);
        super.setEnabled(false);
		this.setDataType(FieldType.Date);
	}

	@Override
	public Date getValue() {
		return (Date) super.getValue();
	}

	public Data setValue(Date value) {
		Object ObjValue = value;
        this.setValue(ObjValue);
		return this;
	}

	public String toJson() throws Exception {
		String result = "";
		if (this.getValue()!=null) {
			Date date = this.getValue();
			if (date!=null) {
				result = StringFunctions.formatDateTime("dd/MM/yyyy", date);
			}
		}
		return result;
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
		Date date = (Date) value;
		super.setValue(date);
	}

}
