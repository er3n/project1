package org.abacus.transaction.shared;

import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;
import org.abacus.definition.shared.constant.EnumList.TraState;

@SuppressWarnings("serial")
public class UnableToFindGlcException  extends UnableToCreateDetailException {

	private DefTypeEnum type;
	private DefTypeEnum item;
	private TraState state;

	public UnableToFindGlcException(DefTypeEnum type, DefTypeEnum item, TraState state) {
		this.item = item;
		this.type = type;
		this.state = state;
	}

	public DefTypeEnum getType() {
		return type;
	}

	public void setType(DefTypeEnum type) {
		this.type = type;
	}

	public DefTypeEnum getItem() {
		return item;
	}

	public void setItem(DefTypeEnum item) {
		this.item = item;
	}

	public TraState getState() {
		return state;
	}

	public void setState(TraState state) {
		this.state = state;
	}

}
