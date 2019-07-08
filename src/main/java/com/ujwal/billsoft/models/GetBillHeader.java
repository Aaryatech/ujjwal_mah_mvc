package com.ujwal.billsoft.models;

import java.io.Serializable;
import java.util.List;

public class GetBillHeader implements Serializable{

	private int billHeaderId;  
	private String invoiceNo;
	private String billDate;
	private String billDateTime;
	private int custId;
	private String custName;
	private String custAddress;
	private String custPhone;
	private String custGstn;
	private String custPan;
	private String custEmail;
	private String custVehNo;
	private String custModelNo;
	private String custRegisNo;
	private String custVinNo;
	private int companyId;
	private String compName;
	private int locId;
	private String locationName;
	private int userId;
	private String userName;
	private float taxPer;
	private float taxableAmt;
	private float sgstAmt;
	private float cgstAmt;
	private float igstAmt;
	private float discAmt;
	private float roundOff;
	private float totaTax;
	private float grandTotal;
	private int status ;
	private String remark;
	private int delStatus;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	private float exFloat1;
	private float exFloat2;
	private String saleType; 
	private String logo;
	private String email;
	private String phoneNo;
	private String gstid;
	private String address;
	private int isSameState;
	List<GetBillDetail> getBillDetail;
	
	public int getIsSameState() {
		return isSameState;
	}
	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustGstn() {
		return custGstn;
	}
	public void setCustGstn(String custGstn) {
		this.custGstn = custGstn;
	}
	public String getCustPan() {
		return custPan;
	}
	public void setCustPan(String custPan) {
		this.custPan = custPan;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustVehNo() {
		return custVehNo;
	}
	public void setCustVehNo(String custVehNo) {
		this.custVehNo = custVehNo;
	}
	
	public String getCustModelNo() {
		return custModelNo;
	}
	public void setCustModelNo(String custModelNo) {
		this.custModelNo = custModelNo;
	}
	public String getCustVinNo() {
		return custVinNo;
	}
	public void setCustVinNo(String custVinNo) {
		this.custVinNo = custVinNo;
	}
	public String getCustRegisNo() {
		return custRegisNo;
	}
	public void setCustRegisNo(String custRegisNo) {
		this.custRegisNo = custRegisNo;
	}
    
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getExInt2() {
		return exInt2;
	}
	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public float getExFloat1() {
		return exFloat1;
	}
	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}
	public float getExFloat2() {
		return exFloat2;
	}
	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}
    
	public List<GetBillDetail> getGetBillDetail() {
		return getBillDetail;
	}
	public void setGetBillDetail(List<GetBillDetail> getBillDetail) {
		this.getBillDetail = getBillDetail;
	}
	public int getBillHeaderId() {
		return billHeaderId;
	}
	public void setBillHeaderId(int billHeaderId) {
		this.billHeaderId = billHeaderId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillDateTime() {
		return billDateTime;
	}
	public void setBillDateTime(String billDateTime) {
		this.billDateTime = billDateTime;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public float getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public float getIgstAmt() {
		return igstAmt;
	}
	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}
	public float getTotaTax() {
		return totaTax;
	}
	public void setTotaTax(float totaTax) {
		this.totaTax = totaTax;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGstid() {
		return gstid;
	}
	public void setGstid(String gstid) {
		this.gstid = gstid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "GetBillHeader [billHeaderId=" + billHeaderId + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate
				+ ", billDateTime=" + billDateTime + ", custId=" + custId + ", custName=" + custName + ", custAddress="
				+ custAddress + ", custPhone=" + custPhone + ", custGstn=" + custGstn + ", custPan=" + custPan
				+ ", custEmail=" + custEmail + ", custVehNo=" + custVehNo + ", custModelNo=" + custModelNo
				+ ", custRegisNo=" + custRegisNo + ", custVinNo=" + custVinNo + ", companyId=" + companyId
				+ ", compName=" + compName + ", locId=" + locId + ", locationName=" + locationName + ", userId="
				+ userId + ", userName=" + userName + ", taxPer=" + taxPer + ", taxableAmt=" + taxableAmt + ", sgstAmt="
				+ sgstAmt + ", cgstAmt=" + cgstAmt + ", igstAmt=" + igstAmt + ", discAmt=" + discAmt + ", roundOff="
				+ roundOff + ", totaTax=" + totaTax + ", grandTotal=" + grandTotal + ", status=" + status + ", remark="
				+ remark + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", saleType="
				+ saleType + ", logo=" + logo + ", email=" + email + ", phoneNo=" + phoneNo + ", gstid=" + gstid
				+ ", address=" + address + ", isSameState=" + isSameState + ", getBillDetail=" + getBillDetail + "]";
	}
	
 
}
