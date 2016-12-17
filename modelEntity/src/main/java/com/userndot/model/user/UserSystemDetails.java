package com.userndot.model.user;

public class UserSystemDetails {

	private String operatingSystem;
	private String operatingSystemVersion;
	private String browser;
	private String environment;// web,mobile,tablet,ios
	private String ipAddress;
	private String country;
	private String state;
	private String city;
	private String macAddressOfGateway;
	private String macAddressOfDevice;

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getOperatingSystemVersion() {
		return operatingSystemVersion;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		this.operatingSystemVersion = operatingSystemVersion;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMacAddressOfGateway() {
		return macAddressOfGateway;
	}

	public void setMacAddressOfGateway(String macAddressOfGateway) {
		this.macAddressOfGateway = macAddressOfGateway;
	}

	public String getMacAddressOfDevice() {
		return macAddressOfDevice;
	}

	public void setMacAddressOfDevice(String macAddressOfDevice) {
		this.macAddressOfDevice = macAddressOfDevice;
	}

	@Override
	public String toString() {
		return "UserSystemDetails [operatingSystem=" + operatingSystem
				+ ", operatingSystemVersion=" + operatingSystemVersion
				+ ", browser=" + browser + ", environment=" + environment
				+ ", ipAddress=" + ipAddress + ", country=" + country
				+ ", state=" + state + ", city=" + city
				+ ", macAddressOfGateway=" + macAddressOfGateway
				+ ", macAddressOfDevice=" + macAddressOfDevice + "]";
	}

}