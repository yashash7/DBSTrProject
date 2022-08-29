package com.dbs.team3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RawData {
	
	public static List<String> ofacList = new ArrayList<String>();
	static {
		ofacList.add("ANUPAMA N");
		ofacList.add("AJAY SHAMU KUMAR");
		ofacList.add("B A SANTOSH");
		ofacList.add("ANIL KUMAR NAGARAJ");
		ofacList.add("AKSHAYA WEALTH MANAGEMENT (P) LTD");
	}
	
	public static Map<String,String> messageMap = new HashMap<String,String>();
	static {
		messageMap.put("CHQB", "Beneficiary customer must be paid by cheque only.");
		messageMap.put("CORT", "Payment is made in settlement for a trade.");
		messageMap.put("HOLD", "Beneficiary customer or claimant will call upon identification.");
		messageMap.put("INTC", "Payment between two companies that belongs to the same group.");
		messageMap.put("PHOB", "Please advise the intermediary institution by phone.");
		messageMap.put("PHOI", "Please advise the intermediary by phone.");
		messageMap.put("PHON", "Please advise the account with institution by phone.");
		messageMap.put("REPA", "Payments has a related e-Payments reference.");
		messageMap.put("SDVA", "Payment must be executed with same day.");
	}
	
	public static Map<String, String> statusMap = new HashMap<String, String>();
	static {
		statusMap.put("insufficientFunds", "FAILED! INSUFFICIENT FUNDS");
		statusMap.put("receiverInTerror", "FAILED! RECEIVER IN TERROR LIST");
		statusMap.put("success", "SUCCESS");
	}
	

}
