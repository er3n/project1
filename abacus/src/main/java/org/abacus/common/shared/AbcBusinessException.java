package org.abacus.common.shared;

@SuppressWarnings("serial")
public abstract class AbcBusinessException extends RuntimeException {

	private String[] params;

	public AbcBusinessException(){
		
	}

	public AbcBusinessException(String... params) {
		this.params = params;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public String[] getParams() {
		return params;
	}

}
