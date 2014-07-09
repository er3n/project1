package org.abacus.definition.shared.constant;

public interface DefConstant {

	enum DefTypeEnum {

		// Item Cards (ACC)
		ITM, //
		ITM_CS, // Cash
		ITM_CC, // CreditCard
		ITM_BN, // Bank
		ITM_TX, // Tax
		ITM_SR, // Services
		ITM_SR_FN, // Fin
		ITM_SR_ST, // Stock
		ITM_SR_FA, // Fas
		ITM_CU, // Customer
		ITM_VE, // Vendor
		ITM_SF, // Staff
		//
		// Budget
		BUD,// 
		BUD_R, // Budget Revenue
		BUD_X, // Budget Expense
		//
		// General Ledger Category
		GLC, //
		GLC_A, // Asset
		GLC_L, // Liability
		GLC_O, // OwnersEquity
		GLC_R, // Revenue
		GLC_X, // Expense
		GLC_A_CS, // Cash
		GLC_A_BN, // Bank
		GLC_A_CR, // CreditCard
		GLC_A_CU_IV, // Customer Sales
		GLC_A_FA, // Fixed Aseets
		GLC_A_SF_AD, // Staff Advance
		GLC_A_ST, // Stock
		GLC_A_VE_AD, // Vendor Advance
		GLC_A_TX, // Tax
		GLC_L_CU_AD, // Customer Advance
		GLC_L_SF_SY, // Staff Salary
		GLC_L_VE_BL, // Vendor Bill
		GLC_L_TX, // Tax
		GLC_R_IV, // Sales
		GLC_R_DS, // Discount
		GLC_X_EX, // Expenses
		GLC_X_DP, // FA Deprecation
		GLC_X_SF, // Staff Expense
		GLC_X_ST, // Stock Expense
		//
		// Journal Voucher Types (ACR)
		JVT, //
		JVT_J, // JournalVoucher TR:Mahsup
		JVT_R, // Receipt TR:Tahsil
		JVT_P, // Payment TR:Tediye
		JVT_S, // SalesInvoices
		JVT_B, // SupplierBills
		JVT_D, // DebitNote (S+) (B-)
		JVT_C, // CreditNote (B+) (S-)
		//
		//
		// Cost (IEC)
		CST, //
		CST_KY, // Cost Keys
		CST_PL, // Cost Places
		CST_TY, // Cost Types (TYP.DEF)
		//
		// Account Plans
		PLN,
		//
		// Finance Transactions
		FIN,
		//
		// Payment
		FIN_PY, //
		FIN_PY_BLP_O, // Bill Payment
		FIN_PY_BLA_O, // Bill Advance
		FIN_PY_STF_O, // Staff Salary
		FIN_PY_SAD_O, // Staff Adv
		FIN_PY_EXP_O, // Expenses
		// Receipt
		FIN_RC, //
		FIN_RC_IVR_I, // Invoice Receipt
		FIN_RC_IVA_I, // Invoice Advance
		// Allocation
		FIN_AL, //
		FIN_AL_PAY_O, // Alloc Payment
		FIN_AL_REC_I, // Alloc Receipt
		// Invoices
		FIN_IN, //
		FIN_IN_IVC, // Invoice
		FIN_IN_BLL, // Bill
		// Cost
		FIN_TR_CST,
		//
		// Stock
		STK, //
		STK_TR, //
		STK_TR_OC, // Open-Close
		STK_TR_OC_I, //
		STK_TR_OC_O, //
		STK_TR_IO, // Input-Output
		STK_TR_IO_I, //
		STK_TR_IO_O, //
		STK_TR_WB, // WayBill
		STK_TR_WB_I, //
		STK_TR_WB_O, //
		STK_TR_TX, // Transfer
		//
		// FAS
		FAS, //
		FAS_OC, // Open-Close
		FAS_OC_I, //
		FAS_OC_O, //
		FAS_IO, // Input-Output
		FAS_IO_I, //
		FAS_IO_O, //
		FAS_WB, // WayBill
		FAS_WB_I, //
		FAS_WB_O, //
		FAS_TX, // Transfer
		;

		@Override
		public String toString() {
			return name();
		}
	}

	enum TrType {
		IN(+1), 
		OUT(-1), 
		DUMMY(0), ;
		private int val;

		private TrType(int val) {
			this.val = val;
		}

		public int getValue() {
			return val;
		}
	};
	
	enum CurrScale {
		num(0), acc(2), stk(3), ;
		private int val;

		private CurrScale(int val) {
			this.val = val;
		}

		public int getValue() {
			return val;
		}
	};

	enum StkCostType {
		FIFO(1L), AVG(2L), AVGM(3L);
		private final Long type;

		private StkCostType(Long t) {
			type = t;
		}

		public Long getType() {
			return type;
		}
	}

	enum StkAuthEnum {
		input_bill, //$NON-NLS-1$
		input_only, //$NON-NLS-1$
		input_send, //$NON-NLS-1$
		output_sales, //$NON-NLS-1$
		output_only, //$NON-NLS-1$
		;
	}

}
// Kisaltma Kodlari
// --------------
// SF Staff
// CS Cash
// BN Bank
// CR CreditCard
// CU Customer
// VE Vendor
// AD Advance
// IV Invoice
// BL Bill
// FA Fas
// ST Stock
// TX Tax
// DS Discount
// EX Expense
// DP Deprecetion
// SR Service
// CS Cost

