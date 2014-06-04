package org.abacus.definition.shared.constant;

public interface EnumList {

	enum CatMenuPeriod implements ISelectionEnum {
		WEEKLY("Haftalık"), //
		MOUNTHLY("Aylık"), //
		;

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
		L0("Holding"), //
		L1("Şirket"), //
		L2("Bölge"), //
		L3("Proje"), //
		;
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
		STK_M("Malzeme"), // Stok.Hammadde
		STK_P("Ürün"), // Stok.Urun
		// FAS_?, //
		// FIN_?, //
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
		S("Stok Deposu"), //
		A("Finansal Birim"), //
		// F("Demirbas Deposu"), //
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
		PRM_STOCK_COSTTYPE, //
		;
	}

	enum DefTypeGroupEnum implements ISelectionEnum {
		PRM("Parametre"), // Static Parameter
		VAL("Kod"), // Dynamic Value Tree
		ITM("Kalem"), // Dynamic Item
		STK("Stok"), // Dynamic Task Stock
		FIN("Finans"), // Dynamic Task Finance
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
		PRM_STOCK("Stok Parametreleri"), //
		// Value
		VAL_CATEGORY("Stok Grupları"), //
		VAL_MEAL("Yemek Öğünleri"), //
		VAL_RECEIPT("Yemek Tipleri"), //
		// Item
		ITM_SR_FN("Finans Hizmetleri"), //
		ITM_SR_ST("Stok Hizmetleri"), //
		ITM_SR_FA("Demirbaş Hizmetleri"), //
		// Stock
		STK_OC("Stok Open/Close"), //
		STK_OC_I("Stok Open"), //
		STK_IO("Stok In/Out"), //
		STK_IO_I("Stok Inputs"), //
		STK_IO_O("Stok Outputs"), //
		STK_WB("Stok WayBill"), //
		STK_WB_I("Stok WayBill IN"), //
		STK_WB_O("Stok WayBill OUT"), //
		STK_TT("Stok Transfer"), //
		// Finance
		FIN_CS("Masraf Giriş"), //
		;
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
		ALL("Finans İşlem"), //
		INP("Depo Giriş"), //
		OUT("Depo Cikiş"), //
		;

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
		WAIT("Beklemede"), //
		DONE("Yapıldı"), //
		CANCEL("Iptal"), //
		;

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
