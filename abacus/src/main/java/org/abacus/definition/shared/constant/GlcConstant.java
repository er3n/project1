package org.abacus.definition.shared.constant;

import org.abacus.definition.shared.constant.EnumList.AccountGLC;
import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;
import org.abacus.definition.shared.constant.EnumList.TraState;

public class GlcConstant {

	public static AccountGLC getAccountGLC(DefTypeEnum type, DefTypeEnum item, TraState state){
		AccountGLC result = null;
		for (FinHolder finHolder : integrationFIN){
			if ((type.equals(finHolder.type)) && (item.equals(finHolder.item)) && (state.equals(finHolder.state))){
				result = finHolder.glc;
				break;
			}
		}
		return result;
	}

	public Boolean isCorrectItemGLC(DefTypeEnum item, AccountGLC glc){
		Boolean result = Boolean.FALSE;
		for (GlcHolder glcHolder : integrationGLC){
			if ((item.equals(glcHolder.item)) && (glc.equals(glcHolder.glc))){
				return Boolean.TRUE;
			}
		}
		return result;
	}
	
	static GlcHolder[] integrationGLC = new GlcHolder[]{
			//Asset
			new GlcHolder(DefTypeEnum.ITM_CS, AccountGLC.GLC_A),//Cashes
			new GlcHolder(DefTypeEnum.ITM_CM_CU, AccountGLC.GLC_A),//Customer Invoice
			new GlcHolder(DefTypeEnum.ITM_SR_ST, AccountGLC.GLC_A),//Inventory Stock 
			new GlcHolder(DefTypeEnum.ITM_CM_VE, AccountGLC.GLC_A),//Vendor Advance
			new GlcHolder(DefTypeEnum.ITM_PE, AccountGLC.GLC_A),//Personnel Advance
			//Liability
			new GlcHolder(DefTypeEnum.ITM_PE, AccountGLC.GLC_L),//Personnel Salary
			new GlcHolder(DefTypeEnum.ITM_CM_VE, AccountGLC.GLC_L),//Vendor Bill
			new GlcHolder(DefTypeEnum.ITM_CM_CU, AccountGLC.GLC_L),//Customer Advance
			//Owners Equity
			//Revenue
			new GlcHolder(DefTypeEnum.ITM_SR_FN, AccountGLC.GLC_R),//Service Sales
			new GlcHolder(DefTypeEnum.ITM_SR_ST, AccountGLC.GLC_R),//Inventory Sales 
			//Expense
			new GlcHolder(DefTypeEnum.ITM_SR_FN, AccountGLC.GLC_X),//Service Purchase
			new GlcHolder(DefTypeEnum.ITM_SR_ST, AccountGLC.GLC_X),//Inventory Expense
	};

	static FinHolder[] integrationFIN = new FinHolder[]{
			//Bill
			new FinHolder(DefTypeEnum.FIN_B , DefTypeEnum.ITM_CM_VE, TraState.OUT, AccountGLC.GLC_L),
			new FinHolder(DefTypeEnum.FIN_B , DefTypeEnum.ITM_SR_FN, TraState.INP, AccountGLC.GLC_X),
			new FinHolder(DefTypeEnum.FIN_B , DefTypeEnum.ITM_SR_ST, TraState.INP, AccountGLC.GLC_A),
			//Sales
			new FinHolder(DefTypeEnum.FIN_S , DefTypeEnum.ITM_CM_CU, TraState.INP, AccountGLC.GLC_A),
			new FinHolder(DefTypeEnum.FIN_S , DefTypeEnum.ITM_SR_FN, TraState.OUT, AccountGLC.GLC_R),
			new FinHolder(DefTypeEnum.FIN_S , DefTypeEnum.ITM_SR_ST, TraState.OUT, AccountGLC.GLC_R),
			//Payment
			new FinHolder(DefTypeEnum.FIN_P , DefTypeEnum.ITM_CS, TraState.OUT, AccountGLC.GLC_A),
			new FinHolder(DefTypeEnum.FIN_P , DefTypeEnum.ITM_CM_VE, TraState.INP, AccountGLC.GLC_L),
			new FinHolder(DefTypeEnum.FIN_P , DefTypeEnum.ITM_PE, TraState.INP, AccountGLC.GLC_L),
			new FinHolder(DefTypeEnum.FIN_P , DefTypeEnum.ITM_SR_FN, TraState.INP, AccountGLC.GLC_X),
			//Receipt
			new FinHolder(DefTypeEnum.FIN_R , DefTypeEnum.ITM_CS, TraState.INP, AccountGLC.GLC_A),
			new FinHolder(DefTypeEnum.FIN_R , DefTypeEnum.ITM_CM_CU, TraState.OUT, AccountGLC.GLC_A),
	};

	static class GlcHolder {
		DefTypeEnum item; AccountGLC glc;
		GlcHolder(DefTypeEnum item, AccountGLC glc){
			this.item = item; this.glc = glc;
		}
	}

	static class FinHolder {
		DefTypeEnum type; DefTypeEnum item; TraState state; AccountGLC glc;
		FinHolder(DefTypeEnum type, DefTypeEnum item, TraState state, AccountGLC glc){
			this.item = item; this.type = type; this.state = state; this.glc = glc;
		}
	}
}

//VALUES('' ,'FIN.INV.SLS' ,'ACC.CACC' ,1 ,'GLC.A.CUST.SLS' );
//VALUES('' ,'FIN.TR.ALC.REC.I' ,'ACC.CACC' ,-1 ,'GLC.A.CUST.SLS' );
//VALUES('' ,'FIN.TR.PAY.PRH.O' ,'ACC.CACC' ,1 ,'GLC.A.CUST.SLS' );
//VALUES('' ,'FIN.TR.REC.SLS.I' ,'ACC.CACC' ,-1 ,'GLC.A.CUST.SLS' );
//VALUES('' ,'FIN.TR.ALC.PAY.O' ,'ACC.CACC' ,-1 ,'GLC.A.SUPL.ADV' );
//VALUES('' ,'FIN.TR.PAY.PRA.O' ,'ACC.CACC' ,1 ,'GLC.A.SUPL.ADV' );
//VALUES('' ,'FIN.TR.ALC.REC.I' ,'ACC.CACC' ,1 ,'GLC.L.CUST.ADV' );
//VALUES('' ,'FIN.TR.REC.SLA.I' ,'ACC.CACC' ,-1 ,'GLC.L.CUST.ADV' );
//VALUES('' ,'FIN.TR.REC.SLS.I' ,'ACC.CACC' ,1 ,'GLC.L.CUST.ADV' );
//VALUES('' ,'FIN.INV.PRH' ,'ACC.CACC' ,-1 ,'GLC.L.SUPL.PRH' );
//VALUES('' ,'FIN.TR.ALC.PAY.O' ,'ACC.CACC' ,1 ,'GLC.L.SUPL.PRH' );
//VALUES('' ,'FIN.TR.REC.SLS.I' ,'ACC.CASH' ,1 ,'GLC.A.CASH' );
//VALUES('' ,'FIN.TR.REC.SLA.I' ,'ACC.CASH' ,1 ,'GLC.A.CASH' );
//VALUES('' ,'FIN.TR.PAY.STA.O' ,'ACC.CASH' ,-1 ,'GLC.A.CASH' );
//VALUES('' ,'FIN.TR.PAY.PRH.O' ,'ACC.CASH' ,-1 ,'GLC.A.CASH' );
//VALUES('' ,'FIN.TR.PAY.PRA.O' ,'ACC.CASH' ,-1 ,'GLC.A.CASH' );
//VALUES('' ,'FIN.TR.PAY.EXP.O' ,'ACC.CASH' ,-1 ,'GLC.A.CASH' );
//VALUES('' ,'FIN.TR.REC.SLA.I' ,'ACC.CRCR' ,1 ,'GLC.A.CRCR' );
//VALUES('' ,'FIN.TR.REC.SLS.I' ,'ACC.CRCR' ,1 ,'GLC.A.CRCR' );
//VALUES('' ,'FIN.INV.SLS' ,'ACC.DISC' ,1 ,'GLC.R.DIS' );
//VALUES('' ,'FIN.TR.ALC.REC.I' ,'ACC.PREP' ,1 ,'GLC.L.CUST.ADV' );
//VALUES('' ,'FIN.TR.REC.SLA.I' ,'ACC.PREP' ,-1 ,'GLC.L.CUST.ADV' );
//VALUES('' ,'FIN.TR.REC.SLS.I' ,'ACC.PREP' ,1 ,'GLC.L.CUST.ADV' );
//VALUES('' ,'FIN.INV.SLS' ,'ACC.ROUND' ,-1 ,'GLC.R.RND' );
//VALUES('' ,'FIN.INV.PRH' ,'ACC.ROUND' ,-1 ,'GLC.R.RND' );
//VALUES('' ,'FIN.INV.PRH' ,'ACC.SRVC.CST' ,1 ,'GLC.X.EXP' );
//VALUES('' ,'FIN.TR.PAY.EXP.O' ,'ACC.SRVC.CST' ,1 ,'GLC.X.EXP' );
//VALUES('' ,'FIN.INV.PRH' ,'ACC.SRVC.FAS' ,1 ,'GLC.A.FASS' );
//VALUES('' ,'FIN.INV.SLS' ,'ACC.SRVC.SRV' ,-1 ,'GLC.R.SLS' );
//VALUES('' ,'FIN.INV.PRH' ,'ACC.SRVC.STK' ,1 ,'GLC.A.STOK' );
//VALUES('' ,'FIN.INV.SLS' ,'ACC.SRVC.STK' ,-1 ,'GLC.R.SLS' );
//VALUES('' ,'FIN.TR.REC.SLS.I' ,'ACC.STAFF' ,1 ,'GLC.A.STAF.ADV' );
//VALUES('' ,'FIN.TR.PAY.STA.O' ,'ACC.STAFF' ,1 ,'GLC.A.STAF.ADV' );
//VALUES('' ,'FIN.TR.REC.SLA.I' ,'ACC.PF.CHEQ.R' ,1 ,'GLC.A.CHEQ' );
//VALUES('' ,'FIN.INV.SLS.UNS' ,'ACC.CACC' ,-1 ,'GLC.A.CUST.UNS' );
//VALUES('' ,'FIN.PF.CHEQ.R' ,'ACC.BANK' ,1 ,'GLC.A.BANK' );
//VALUES('' ,'FIN.PF.CHEQ.R' ,'ACC.PF.CHEQ.R' ,-1 ,'GLC.A.CHEQ' );
//VALUES('' ,'FIN.INV.SLS.FIN' ,'ACC.BANK' ,1 ,'GLC.A.BANK' );
//VALUES('' ,'FIN.INV.SLS.FIN' ,'ACC.CACC' ,1 ,'GLC.A.SUPL.ADV' );
//VALUES('' ,'FIN.TR.PAY.STA.O' ,'ACC.BANK' ,-1 ,'GLC.A.BANK' );
//VALUES('' ,'FIN.TR.PAY.STA.O' ,'ACC.PF.CHEQ.P' ,-1 ,'GLC.A.CHEQ' );
