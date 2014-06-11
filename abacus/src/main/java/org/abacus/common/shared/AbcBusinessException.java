package org.abacus.common.shared;


@SuppressWarnings("serial")
public class AbcBusinessException extends RuntimeException {

	private String message;
	private String[] parameters;

	public AbcBusinessException(String message, String... parameters) {
		this.message = message;
		this.parameters = parameters;
	}

	public AbcBusinessException() {
		this.message = this.getClass().getName();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

}
