package com.userndot.model.user;

public class CustomUserField {

	private String fieldName;
	private String fieldValue;
	private CustomUserFieldType fieldType;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public CustomUserFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(CustomUserFieldType fieldType) {
		this.fieldType = fieldType;
	}

	@Override
	public String toString() {
		return "CustomUserField [fieldName=" + fieldName + ", fieldValue="
				+ fieldValue + ", fieldType=" + fieldType + "]";
	}
}
