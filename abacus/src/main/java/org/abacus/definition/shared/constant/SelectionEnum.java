package org.abacus.definition.shared.constant;

public class SelectionEnum implements DefConstant.ISelectionEnum {

	private String name;
	private String description;
	
	public SelectionEnum(String n){
		this.name = n;
	}

	public SelectionEnum(DefConstant.ISelectionEnum ise){
		this.name = ise.name();
		this.description = ise.getDescription();
	}

	public String name() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj){
		return this.name().equals(((SelectionEnum)obj).name());
	}
	
}
