package org.abacus.definition.shared.constant;

public interface EnumList {

	enum OrgCompanyLevelEnum implements ISelectionEnum {
		L1("Holding"),
		L2("Company"),
		L3("Project"),
		;
		private String description;
		
		private OrgCompanyLevelEnum(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return this.description;
		}
		
	}
	
	enum OrgDepartmentGroupEnum implements ISelectionEnum {
		A("Administrative"),
		S("Store"),
		;
		private String description;
		
		private OrgDepartmentGroupEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}
		
	}
	
	enum DefTypeGroupEnum implements ISelectionEnum {
		P("Parameter"),	//(Static)
		S("State"),		//(Static)
		T("Task"),		//(Dynamic)
		V("Value"),		//(Dynamic)
		;
		private String description;
		
		private DefTypeGroupEnum(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return this.description;
		}
		
	}

}
