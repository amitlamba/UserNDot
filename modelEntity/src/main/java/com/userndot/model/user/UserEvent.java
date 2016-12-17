package com.userndot.model.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserEvent {

	private int eventID;
	private String eventName;
	private String userID;
	private String clientID;
	private Date eventTime;
	private UserEventType eventType;
	private String eventFlowType;// For A/B Testing
	private String url;
	private String referrer;
	private String stayTimeSeconds;
	private List<Map<String, String>> customFields;

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public UserEventType getEventType() {
		return eventType;
	}

	public void setEventType(UserEventType eventType) {
		this.eventType = eventType;
	}

	public String getEventFlowType() {
		return eventFlowType;
	}

	public void setEventFlowType(String eventFlowType) {
		this.eventFlowType = eventFlowType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getStayTimeSeconds() {
		return stayTimeSeconds;
	}

	public void setStayTimeSeconds(String stayTimeSeconds) {
		this.stayTimeSeconds = stayTimeSeconds;
	}

	public List<Map<String, String>> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<Map<String, String>> customFields) {
		this.customFields = customFields;
	}

	@Override
	public String toString() {
		return "UserEvent [eventID=" + eventID + ", eventName=" + eventName
				+ ", userID=" + userID + ", clientID=" + clientID
				+ ", eventTime=" + eventTime + ", eventType=" + eventType
				+ ", eventFlowType=" + eventFlowType + ", url=" + url
				+ ", referrer=" + referrer + ", stayTimeSeconds="
				+ stayTimeSeconds + ", customFields=" + customFields + "]";
	}
}
