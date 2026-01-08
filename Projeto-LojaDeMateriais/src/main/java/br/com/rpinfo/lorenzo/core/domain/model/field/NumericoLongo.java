package br.com.rpinfo.lorenzo.core.domain.model.field;

import br.framework.classes.DataBase.EntityFieldShort;
import br.framework.classes.helpers.Types.FieldType;

import java.io.Serializable;


public class NumericoLongo extends EntityFieldShort implements Serializable {

	/**
   *
   */
  private static final long serialVersionUID = 5237978494722523252L;

	public NumericoLongo() {
		this(true);
	}

	public NumericoLongo(Boolean  autoEnabled) {
        super(autoEnabled);
		super.setValue(0L);
        super.setEnabled(false);
		this.setDataType(FieldType.Long);
	}

	@Override
	public Long getValue() {
		return (Long) super.getValue();
	}

	@Override
	public void setValue(Object value) {
		this.setHasModification(Boolean.TRUE);
		Long longValue = (Long) value;
		super.setValue(longValue);
	}
	
	public NumericoLongo setValue(Long value) {
		Object ObjValue = value;
		this.setValue(ObjValue);
		return this;
	}

}
