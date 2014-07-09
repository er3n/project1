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
		L0("Holding (0)"), //
		L1("Şirket (1)"), //
		L2("Proje (2)"), //
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
		STK_P("Yemek"), // Stok.Urun
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
		F("Finansal Birim"), //
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
		NIL("Null"), 
		PRM("Parametre"), // Static Parameter
		VAL("Kod"), // Dynamic Value Tree
		ITM("Kalem"), // Dynamic Item
		STK("Stok"), // Dynamic Task Stock
		FIN("Finans"), // Dynamic Task Finance
		ACC("Acc"), // Acc
		REQ("İstek")
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
		PRM_STOCK("Stok Parametreleri", 0), //
		// Value
		VAL_CATEGORY("Stok Grupları", 0), //
		VAL_MEAL("Yemek Öğünleri", 0), //
		VAL_RECEIPT("Yemek Tipleri", 0), //
		// Item
		ITM("All Items", 0), //
		ITM_SR("All Services", 0), //
		ITM_SR_FN("Finans Gider Tanımları", 0), //
		ITM_SR_ST("Malzeme Tanımları", 0), //
		ITM_VE("Firma Tanımları", 0), //
		ITM_PE("Personel Tanımları", 0), //
		ITM_CU("Müşteri Tanımları", 0), //
		ITM_CA("Parasal Tanımlar", 0), //
		// Stock
		STK_WB("İrsaliye İşlem Tip",0), //
		STK_WB_I("Stok İrsaliye Giriş Tip", 1), //
		STK_WB_O("Stok Satış Çıkş Tip", -1), //
		STK_IO("Stok İşlem Tip",0), //
		STK_IO_S("Stok Açılış Tip", 1), //
		STK_IO_I("Stok İşlem Giriş Tip", 1), //
		STK_IO_O("Stok İşlem Çıkış Tip", -1), //
		STK_IO_T("Stok Transfer Tip", 0), //
		// Finance,Account
		FIN_B("Alış Fatura", +1), //
		FIN_S("Satış Fatura", -1), //
		FIN_P("Ödeme", -1), //
		FIN_R("Tahsil", +1), //
		FIN_J("Mahsup", 0), //
		//Request
		REQ_IO("Stok istek tip",0),
		REQ_IO_T("Stok transfer istek tip", 0), //
		REQ_IO_P("Stok satın alma istek tip",0),


		NIL("?",0), //
		;
		private String description;
		private Integer state;

		private DefTypeEnum(String description, Integer state) {
			this.description = description;
			this.state = state;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		@Override
		public String getName() {
			return this.name();
		}

		public Integer getState() {
			return this.state;
		}

		public DefTypeGroupEnum getTypeGroupEnum() {
			String grp = this.name().substring(0, 3);
			return DefTypeGroupEnum.valueOf(grp); 
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

	enum TraState implements ISelectionEnum {
		INP("Giriş"), OUT("Çıkış");

		private String description;

		private TraState(String description) {
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

		public Integer value() {
			if (this.equals(INP)) {
				return +1;
			} else if (this.equals(OUT)) {
				return -1;
			}
			return 0;
		}
	}

	enum RoundScale {
		NUM(0), ACC(2), STK(3), ;
		private int val;

		private RoundScale(int val) {
			this.val = val;
		}

		public int getValue() {
			return val;
		}
	};

	enum Hierachy {
		PARENT, CHILD
	};
	
	enum EntityStatus{
		NEW,UPDATE,DELETE;
	}
	
	enum RequestStatus implements ISelectionEnum {
		PREPARE("Hazırlanıyor","Hazırlanıyor"),REQUEST("Onay bekleniyor","İhale başladı"),DONE("Onaylandı","İhale tamamlandı"),PARTIALLY("Kısmen onaylandı","İhale değerlendiriliyor"),CANCEL("Reddedildi","İptal edildi");
		
		private String description;
		private String pdescription;

		private RequestStatus(String description,String pdescription) {
			this.description = description;
			this.pdescription = pdescription;
		}

		@Override
		public String getDescription() {
			return this.description;
		}
		
		public String getPDescription() {
			return this.pdescription;
		}

		@Override
		public String getName() {
			return this.name();
		}
		
	}

}
