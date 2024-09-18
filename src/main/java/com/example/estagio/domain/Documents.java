package com.example.estagio.domain;

import java.io.Serializable;
import java.util.Date;

import com.example.estagio.domain.enums.DocumentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class Documents implements Serializable{
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String documentId;

    private String documentName;
    private Integer documentType;
    private Long documentSize;
    private Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    
    private String fileUrl;
    
    public Documents() {}

    public Documents(String documentId, String documentName, Long documentSize, Date uploadDate, Order order,
            String fileUrl, DocumentType documentType) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.documentSize = documentSize;
        this.uploadDate = uploadDate;
        this.order = order;
        this.fileUrl = fileUrl;
        setLicenseType(documentType);
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(Long documentSize) {
        this.documentSize = documentSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public DocumentType getDocumentType(){
        return DocumentType.valueOf(documentType); 
    }
    public void setLicenseType(DocumentType documentType){
        if (documentType != null) this.documentType = documentType.getCode();
    }
}
