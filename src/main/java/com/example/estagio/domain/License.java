package com.example.estagio.domain;

import java.io.Serializable;
import java.util.Date;

import com.example.estagio.domain.enums.LicenseType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "licenses")
public class License implements Serializable{
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String licenseId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "applicantId", nullable = false)
    private User applicant;

    private Integer licenseType;
    private Date issueDate;

    public License() {}

    public License(String licenseId, Order order, User applicant, Date issueDate, LicenseType licenseType) {
        this.licenseId = licenseId;
        this.applicant = applicant;
        this.order = order;
        this.applicant = applicant;
        this.issueDate = issueDate;
        setLicenseType(licenseType);
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrderId(Order order) {
        this.order = order;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public LicenseType getLicenseType(){
        return LicenseType.valueOf(licenseType); 
    }
    public void setLicenseType(LicenseType licenseType){
        if (licenseType != null) this.licenseType = licenseType.getCode();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((licenseId == null) ? 0 : licenseId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        License other = (License) obj;
        if (licenseId == null) {
            if (other.licenseId != null)
                return false;
        } else if (!licenseId.equals(other.licenseId))
            return false;
        return true;
    }
    
}
