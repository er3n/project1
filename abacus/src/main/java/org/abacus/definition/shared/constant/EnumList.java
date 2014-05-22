package org.abacus.definition.shared.constant;

public interface EnumList {

	enum CatMenuPeriod implements ISelectionEnum {
		WEEKLY("Haftalik"), MOUNTHLY("Aylik");

		private CatMenuPeriod(String desc) {
			this.descripion = desc;
		}

		private String descripion;

		@Override
		public String getName() {
			return this.name();
		}

		@Override
		public String getDescription() {
			return descripion;
		}

	}

	enum OrgOrganizationLevelEnum implements ISelectionEnum {
		L0("Holding"), L1("Company"), L2("Section"), L3("Project"), ;
		private String description;

		private OrgOrganizationLevelEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}

	}

	enum DefItemClassEnum implements ISelectionEnum {
		STK_M("Material"), // Stok.Hammadde
		STK_P("Product"), // Stok.Urun
		// FAS_?
		// FIN_?
		;
		private String description;

		private DefItemClassEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}
	}

	enum OrgDepartmentGroupEnum implements ISelectionEnum {
		A("Administrative"), S("InventoryStore"),
		// F("FixedAssetStore"),
		;
		private String description;

		private OrgDepartmentGroupEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}

	}

	enum DefParameterEnum {
		PRM_STOCK_COSTTYPE, ;
	}

	enum DefTypeGroupEnum implements ISelectionEnum {
		PRM("Parameter"), // Static Parameter
		VAL("Value"), // Dynamic Value Tree
		ITM("Item"), // Dynamic Item
		STK("Stk Task"), // Dynamic Task Stock
		FIN("Fin Task"), // Dynamic Task Finance
		;
		private String description;

		private DefTypeGroupEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}
	}

	enum DefTypeEnum implements ISelectionEnum {
		// Parameter
		PRM_STOCK("Stok Parametreleri"),
		// Value
		VAL_SEHIR("Sehirler"), VAL_TAT("Tat Tipleri"), VAL_CATEGORY("Stok Kategorileri"), VAL_MEAL("Yemek Ögünleri"),
		// Item
		ITM_SR_FN("Finans Hizmetleri"), ITM_SR_ST("Stok Hizmetleri"), ITM_SR_FA("Demirbaslar"),
		// Stock
		STK_OC("Stok Open/Close"), STK_OC_I("Stok Open"), STK_IO("Stok In/Out"), STK_IO_I("Stok Inputs"), STK_IO_O("Stok Outputs"), STK_WB("Stok WayBill"), STK_WB_I("Stok WayBill IN"), STK_WB_O("Stok WayBill OUT"), STK_TT("Stok Transfer"),
		// Finance
		FIN_CS("Masraf Giris"), ;
		private String description;

		private DefTypeEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}
	}

	enum DepartmentAuthEnum implements ISelectionEnum {
		ALL("Finans"), INP("Depo Giris"), OUT("Depo Cikis"), ;

		private String description;

		private DepartmentAuthEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}
	}

	enum MenuStatusEnum implements ISelectionEnum {
		WAIT("Beklemede"), DONE("Yapildi"), CANCEL("Iptal"), ;

		private String description;

		private MenuStatusEnum(String description) {
			this.description = description;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}
	}

}
