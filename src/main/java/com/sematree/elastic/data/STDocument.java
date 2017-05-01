package com.sematree.elastic.data;

public class STDocument {
	private String reportNumber;
	private String dateOfIssue;
	private String applicantName;
	private String applicantAddress;
	private String standard;
	private String testProcedure;
	private String nonStandardTestMethod;
	private String testReportFormNumber;
	private String testItemDescription;
	private String tradeMark;
	private String manufacturer;
	private String modelTypeReference;
	private String ratings;
	private String contents;
	
	private STDocumentMetadata metadata;
	
	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantAddress() {
		return applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getTestProcedure() {
		return testProcedure;
	}

	public void setTestProcedure(String testProcedure) {
		this.testProcedure = testProcedure;
	}

	public String getNonStandardTestMethod() {
		return nonStandardTestMethod;
	}

	public void setNonStandardTestMethod(String nonStandardTestMethod) {
		this.nonStandardTestMethod = nonStandardTestMethod;
	}

	public String getTestReportFormNumber() {
		return testReportFormNumber;
	}

	public void setTestReportFormNumber(String testReportFormNumber) {
		this.testReportFormNumber = testReportFormNumber;
	}

	public String getTestItemDescription() {
		return testItemDescription;
	}

	public void setTestItemDescription(String testItemDescription) {
		this.testItemDescription = testItemDescription;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelTypeReference() {
		return modelTypeReference;
	}

	public void setModelTypeReference(String modelTypeReference) {
		this.modelTypeReference = modelTypeReference;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public STDocumentMetadata getMetadata() {
		return metadata;
	}
	
	public void setMetadata(STDocumentMetadata metadata) {
		this.metadata = metadata;
	}
	
}
