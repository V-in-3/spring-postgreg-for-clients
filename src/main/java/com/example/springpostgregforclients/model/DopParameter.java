package com.example.springpostgregforclients.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="dop_parameters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DopParameter {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long paramId;

    @Column(name = "client_id", insertable = false, updatable = false)
    private int clientId;

    @Column(name = "type")
    private int type;

    @Column(name = "value")
    private int value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    public DopParameter() {
    }

    public DopParameter(int clientId, int type, int value) {
        this.clientId = clientId;
        this.type = type;
        this.value = value;
    }

    public long getParamId() {
        return paramId;
    }

    public void setParamId(long paramId) { this.paramId = paramId; }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }
}
