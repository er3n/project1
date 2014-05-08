package org.abacus.common.shared.holder;

import java.io.Serializable;

public class KeyValue implements Serializable {

	private String code;

	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
