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
		PRM("Parameter"),	//Static Parameter
		STT("State"),		//Static State
		STK("Stk Task"),	//Dynamic Task Stock
		FIN("Fin Task"),	//Dynamic Task Finance
		VAL("Value"),		//Dynamic Value Tree
		ACC("Account"),		//Dynamic Account
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
