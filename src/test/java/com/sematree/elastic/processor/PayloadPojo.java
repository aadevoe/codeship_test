package com.sematree.elastic.processor;

import java.util.Date;

/**
 * {
   "reportNumber": "101221895CRT-004",
   "dateOfIssue": "November 16, 2014",
   "applicantName": "Innovative Concepts in Entertainment",
 * @author adevoe
 *
 */
public class PayloadPojo {
	private String reportNumber = null;
	private String dateOfIssue = null;  // Cuz as date blows chunks - search doesn't like format.
	private String applicantName = null;
	private PayloadMetaDataPojo metadata = null;
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
	public PayloadMetaDataPojo getMetadata() {
		return metadata;
	}
	public void setMetadata(PayloadMetaDataPojo metadata) {
		this.metadata = metadata;
	}
}
