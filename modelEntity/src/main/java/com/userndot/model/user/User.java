package com.userndot.model.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by amit on 4/11/16.
 */
public class User {
	private int clientID;
	private String clientKey;
	private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Gender gender;
    private Date dateOfBirth;
    private int age;
    private List<CustomUserField> customUserFields;
    private UserSystemDetails userSystemDetails;
    private Map<Date, UserEvent> userEventsMap;
    private Map<Date, UserTransaction> userTransactionsMap;

    public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<CustomUserField> getCustomUserFields() {
		return customUserFields;
	}

	public void setCustomUserFields(List<CustomUserField> customUserFields) {
		this.customUserFields = customUserFields;
	}

	public UserSystemDetails getUserSystemDetails() {
		return userSystemDetails;
	}

	public void setUserSystemDetails(UserSystemDetails userSystemDetails) {
		this.userSystemDetails = userSystemDetails;
	}

	public Map<Date, UserEvent> getUserEventsMap() {
		return userEventsMap;
	}

	public void setUserEventsMap(Map<Date, UserEvent> userEventsMap) {
		this.userEventsMap = userEventsMap;
	}

	public Map<Date, UserTransaction> getUserTransactionsMap() {
		return userTransactionsMap;
	}

	public void setUserTransactionsMap(
			Map<Date, UserTransaction> userTransactionsMap) {
		this.userTransactionsMap = userTransactionsMap;
	}

	@Override
	public String toString() {
		return "User [clientID=" + clientID + ", clientKey=" + clientKey
				+ ", userID=" + userID + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", customUserFields="
				+ customUserFields + ", userSystemDetails=" + userSystemDetails
				+ ", userEventsMap=" + userEventsMap + ", userTransactionsMap="
				+ userTransactionsMap + "]";
	}
}
