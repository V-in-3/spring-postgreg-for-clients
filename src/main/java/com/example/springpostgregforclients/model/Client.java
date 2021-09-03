package com.example.springpostgregforclients.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long clientId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Group_Id")
    private int groupId;

    @Column(name = "Nalog_Code")
    private String nalogCode;

    @Column(name = "Pass_Ser")
    private String passSer;

    @Column(name = "Pass_Num")
    private String passNum;

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DopParameter> dop_parameters;

    public Client(){}

    public Client(String name, int groupId, String nalogCode, String passSer, String passNum, Set dop_parameters) {
        this.name = name;
        this.groupId = groupId;
        this.nalogCode = nalogCode;
        this.passSer = passSer;
        this.passNum = passNum;
        this.dop_parameters = dop_parameters;
    }

    @Override
    public String toString(){
        return "Client [id =]" + clientId + ", Name" + name + ", GroupId" + groupId + ", NalogCode" + nalogCode + ", PassSer" + passSer + ", PassNum";
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNalogCode() {
        return nalogCode;
    }

    public void setNalogCode(String nalogCode) {
        this.nalogCode = nalogCode;
    }

    public String getPassSer() {
        return passSer;
    }

    public void setPassSer(String passSer) {
        this.passSer = passSer;
    }

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    public Set<DopParameter> getDop_parameters() {
        return dop_parameters;
    }

    public void setDop_parameters(Set<DopParameter> dop_parameters) {
        this.dop_parameters = dop_parameters;
    }
}

