package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by dgup27 on 5/6/2017.
 */
@Entity
@Table(name = "CLIENT")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client extends BaseEntity{

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private int id;

    @NotEmpty
    @Column(name = "CLIENT_NAME", nullable = false)
    private String clientName;

    @Column(name = "CLIENT_EMAIL", nullable = true)
    private String emailID;

    @Column(name = "CLIENT_CONTACT_NUMBER", nullable = true)
    private String clientContactNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getClientContactNumber() {
        return clientContactNumber;
    }

    public void setClientContactNumber(String clientContactNumber) {
        this.clientContactNumber = clientContactNumber;
    }
}