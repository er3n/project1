package org.abacus.definition.shared.constant;

public class SelectionEnum implements ISelectionEnum {

	private String name;
	private String description;
	
	public SelectionEnum(String n){
		this.name = n;
	}

	public SelectionEnum(ISelectionEnum isEnum){
		this.name = isEnum.name();
		this.description = isEnum.getDescription();
	}

	@Override
	public String name() {
		return name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj){
		return this.name().equals(((SelectionEnum)obj).name());
	}

}
