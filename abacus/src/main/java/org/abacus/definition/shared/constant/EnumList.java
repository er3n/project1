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
		L2("Proje"), //
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
		STK_M("Malzeme"), // Stock Material
		STK_P("Yemek"), // Stock Product
		FIN_R("Gelir"), //Revenue Item
		FIN_X("Gider"), //Expense Item
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
		F("Ofis"), //
		S("Depo"), //
		SP("Satınalma"), //
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
		ITM("Hesap"), // Dynamic Item
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
		VAL_CATEGORY("Grup/Kategori", 0), //
		VAL_MEAL("Yemek Öğünleri", 0), //
		VAL_RECEIPT("Yemek Tipleri", 0), //
		// Item
		ITM("All Items", 0), //
		ITM_PE("Personel Hesapları", 0), //
		ITM_CS("Parasal Hesaplar", 0), //
		ITM_CM_VE("Satıcı Hesapları", 0), //
		ITM_CM_CU("Müşteri Hesapları", 0), //
		ITM____VE("Satıcı Avans", 0), //
		ITM____CU("Müşteri Avans", 0), //
		ITM_SR("All Services", 0), //
		ITM_SR_FN("Gelir/Gider Hesapları", 0), //
		ITM_SR_ST("Stok Malzeme/Ürün", 0), //
		// Stock
		STK("Stock",0), //
		STK_WB("İrsaliye İşlem",0), //
		STK_WB_I("Alış İrsaliye", 1), //
		STK_WB_O("Satış İrsaliye", -1), //
		STK_IO("Stok İşlem",0), //
		STK_IO_S("Stok Açılış", 1), //
		STK_IO_I("Stok Giriş", 1), //
		STK_IO_O("Stok Çıkış", -1), //
		STK_IO_T("Stok Transfer", 0), //
		// Finance,Account
		FIN("Finance", 0), //
		FIN_B("Alış Fatura", +1), //
		FIN_S("Satış Fatura", -1), //
		FIN_P("Ödeme", -1), //
		FIN_R("Tahsil", +1), //
		FIN_J("Mahsup", 0), //
		//Request
		REQ_IO("Transfer/Satınalma İstek",0),
		REQ_IO_T("Stok Transfer İstek", 0), //
		REQ_IO_P("Satınalma İstek",0), //

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
		INP("Giriş"), //
		OUT("Çıkış"); //

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
		PREPARE("Hazırlanıyor","Hazırlanıyor"),
		REQUEST("Onay bekleniyor","Teklif bekleniyor"),
		DONE("Onay verildi","Teklifler girildi"),
		REVIEW("Değerlendiriliyor","Değerlendiriliyor"),
		PARTIALLY("Kısmen onaylandı","Kısmen onaylandı"),		
		CANCEL("Reddedildi","İptal edildi")
		;
		
		private String description;
		private String pDescription;

		private RequestStatus(String description,String pDescription) {
			this.description = description;
			this.pDescription = pDescription;
		}

		@Override
		public String getDescription() {
			return this.description+" (S"+ordinal()+")";
		}
		
		public String getPDescription() {
			return this.pDescription+" (P"+ordinal()+")";
		}

		@Override
		public String getName() {
			return this.name();
		}
		
	}

	enum JRList implements ISelectionEnum {
		JRUser("Kullanıcı Listesi"),//
		JROrganization("Organizasyon Listesi"),//
		;
		private String description;
		private JRList(String description) {
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

	enum JRExport implements ISelectionEnum {
		PDF("Pdf"),//
		HTML("Html"),//
		;
		private String description;
		private JRExport(String description) {
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

	enum BudgetType implements ISelectionEnum {
		ESTIMATE("Tahmini"),//
		ACCRUE("Gerçekleşen"), //
		;

		private BudgetType(String desc) {
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
	
	enum BudgetRX implements ISelectionEnum {
		BUD_R("Gelir"),//
		BUD_X("Gider"), //
		;

		private BudgetRX(String desc) {
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
	
}

//Kisaltma Kodlari
//--------------
//SF Staff
//CS Cash
//BN Bank
//CR CreditCard
//CU Customer
//VE Vendor
//AD Advance
//IV Invoice
//BL Bill
//FA Fas
//ST Stock
//TX Tax
//DS Discount
//EX Expense
//DP Deprecetion
//SR Service
//CS Cost
