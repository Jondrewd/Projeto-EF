package com.example.estagio.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.estagio.domain.enums.LicenseType;
import com.example.estagio.domain.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable{
    private static final long serialVersionUID =1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "applicantId", nullable = false)
    private User applicant;

    private String applicantName;
    private Date submissionDate;
    private Integer licenseType;
    private Integer status;

    @OneToMany(mappedBy = "order")
    private List<Documents> documents;

    public Order() {}

    public Order(String orderId, User applicant, String applicantName, Date submissionDate,
             LicenseType licenseType, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.applicant = applicant;
        this.applicantName = applicantName;
        this.submissionDate = submissionDate;

        setLicenseType(licenseType);
        setOrderStatus(orderStatus);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getApplicantId() {
        return applicant;
    }

    public void setApplicantId(User applicant) {
        this.applicant = applicant;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public List<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }
    
    public LicenseType getLicenseType(){
        return LicenseType.valueOf(licenseType); 
    }
    public void setLicenseType(LicenseType licenseType){
        if (licenseType != null) this.licenseType = licenseType.getCode();
    }

    public OrderStatus getOrderStatus(){
        return OrderStatus.valueOf(status); 
    }
    public void setOrderStatus(OrderStatus status){
        if (status != null) this.status = status.getCode();
    }

}
