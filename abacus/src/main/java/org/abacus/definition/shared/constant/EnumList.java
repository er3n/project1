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
	
	enum DefItemClassEnum implements ISelectionEnum {
		STK_M("Material"),	//Stok.Hammadde
		STK_P("Product"),	//Stok.Urun
//		FAS_?		
//		FIN_?
		;
		private String description;
		
		private DefItemClassEnum(String description) {
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
		VAL("Value"),		//Dynamic Value Tree
		ITM("Item"),		//Dynamic Item
		STK("Stk Task"),	//Dynamic Task Stock
		FIN("Fin Task"),	//Dynamic Task Finance
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
	
	enum DefItemTypeEnum implements ISelectionEnum {
		ITM_SR_FN("Finans Hizmetleri"), // Fin
		ITM_SR_ST("Stok Hizmetleri"), // Stock
		ITM_SR_FA("Demirbaslar"); // Fas;
		private String description;
		
		private DefItemTypeEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}
		
	}

}
