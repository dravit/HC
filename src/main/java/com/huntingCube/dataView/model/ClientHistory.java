package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dgup27 on 5/6/2017.
 */
@Entity
@Table(name = "CLIENT_HISTORY")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientHistory extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_HISTORY_ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_STATUS_ID")
    private ClientStatus clientStatus;

    @NotNull
    @Column(name = "RESOURCE_ID", nullable = false)
    private int resourceID;

    @Column(name = "ADDED_DATE")
    private Date addedDate;

    @Column(name = "CLIENT_REMARKS")
    private String remarks;

    @Column(name = "POSITION")
    private String position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ClientHistory{" +
                "id=" + id +
                ", client=" + client +
                ", clientStatus=" + clientStatus +
                ", resourceID=" + resourceID +
                ", addedDate=" + addedDate +
                ", remarks='" + remarks + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
