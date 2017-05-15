package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dgup27 on 5/12/2017.
 */
@Entity
@Table(name = "CLIENT_POSITION")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientPosition extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_POSITION_ID")
    private int id;

    @NotEmpty
    @Column(name = "CLIENT_POSITION", nullable = false)
    private String clientPosition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientPosition() {
        return clientPosition;
    }

    public void setClientPosition(String clientPosition) {
        this.clientPosition = clientPosition;
    }

    @Override
    public String toString() {
        return "ClientPosition{" +
                "id=" + id +
                ", clientPosition='" + clientPosition + '\'' +
                '}';
    }
}
