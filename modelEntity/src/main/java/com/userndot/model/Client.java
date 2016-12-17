package com.userndot.model;

import com.userndot.constants.DBConstants;

/**
 * Created by amit on 4/11/16.
 */
public class Client {
    private int clientID;
    private String name;
    private String contactEmail;
    private String contactPersonName;
    private String username;
    private String contactNumber;
    private int isdCode;
    private Country country;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", name='" + name + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPersonName='" + contactPersonName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
