package com.ujwal.billsoft.models;

public class GetPartsForBill {
	
	private int partId;
	private String partName;
	private String partNo;
	private String partRoNo; //model no.
	
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartRoNo() {
		return partRoNo;
	}
	public void setPartRoNo(String partRoNo) {
		this.partRoNo = partRoNo;
	}
	
	@Override
	public String toString() {
		return "GetPartsForBill [partId=" + partId + ", partName=" + partName + ", partNo=" + partNo + ", partRoNo="
				+ partRoNo + "]";
	}
	
}
