package com.sematree.elastic.data;

import java.text.DecimalFormat;
import java.util.Date;

public class STESMetadata {
	private String documentName;
	private String author;
	private String modifiedBy;
	private String revisionNumber;
	private String uploadedBy;

	private Date dateCreated;
	private Date dateModified;
	private Date dateUploaded;

	private String lineCount;
	private String wordCount;
	private String pageCount;
	private String characterCount;
	private String characterCountWithSpaces;
	
	private String mimeType;
	private long fileSize;
	private String fileSizeReadable;
	
	private String parentUuid;
	private String saffUuid;

	public String getDocumentName() {
		return documentName;
	}
	
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(String revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(Date dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public int getLineCount() {
		if (lineCount == null) {
			lineCount = "0";
		}
		
		return Integer.parseInt(lineCount);
	}

	public void setLineCount(String lineCount) {
		this.lineCount = lineCount;
	}

	public int getWordCount() {
		if (wordCount == null) {
			wordCount = "0";
		}
		
		return Integer.parseInt(wordCount);
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	public int getPageCount() {
		if (pageCount == null) {
			pageCount = "0";
		}
		
		return Integer.parseInt(pageCount);
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public int getCharacterCount() {
		if (characterCount == null) {
			characterCount = "0";
		}
		
		return Integer.parseInt(characterCount);
	}

	public void setCharacterCount(String characterCount) {
		this.characterCount = characterCount;
	}

	public int getCharacterCountWithSpaces() {
		if (characterCountWithSpaces == null) {
			characterCountWithSpaces = "0";
		}
		
		return Integer.parseInt(characterCountWithSpaces);
	}

	public void setCharacterCountWithSpaces(String characterCountWithSpaces) {
		this.characterCountWithSpaces = characterCountWithSpaces;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
		
		fileSizeReadable = getFileSize(fileSize);
	}
	
	public String getFileSizeReadable() {
		return fileSizeReadable;
	}
	
	// HELPER METHODS
	private String getFileSize(long size) {
		if (size <= 0) {
			return "0";
		}
		
	   DecimalFormat df = new DecimalFormat("0.00");
		
		float kilobyte = 1024f;
		float megabyte = kilobyte * 1024;
		float gigabyte = megabyte * 1024;
		
		if (size < kilobyte) {
			return df.format(size) + " B";
		}else if (size >= kilobyte && size < megabyte) {
			return df.format(size / kilobyte) + " KB";
		}else if (size >= megabyte && size < gigabyte) {
			return df.format(size / megabyte) + " MB";
		}else {
			return df.format(size / gigabyte) + " GB";
		}
	}

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	public String getSaffUuid() {
		return saffUuid;
	}

	public void setSaffUuid(String saffUuid) {
		this.saffUuid = saffUuid;
	}
}
