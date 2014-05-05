package org.abacus.definition.shared.constant;

public interface EnumList {

	enum OrgOrganizationLevelEnum implements ISelectionEnum {
		L0("Holding"),
		L1("Company"),
		L2("Section"),
		L3("Project"),
		;
		private String description;
		
		private OrgOrganizationLevelEnum(String description) {
			this.description = description;
		}
		
		@Override
		public String getDescription() {
			return this.description;
		}
		
	}
	
	enum OrgDepartmentGroupEnum implements ISelectionEnum {
		A("Administrative"),
		S("InventoryStore"),
//		F("FixedAssetStore"),
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
