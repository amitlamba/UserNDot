package com.userndot.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.userndot.exception.UserNDotException;

@Named
public class JsonConverter {

	private Logger logger = LoggerFactory.getLogger(JsonConverter.class);

	// http://wiki.fasterxml.com/JacksonInFiveMinutes
	private ObjectMapper objectMapper = new ObjectMapper();

	// used to convert returned JSON into a Map of String, String
	private TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {
	};

	// used to convert returned JSON into a Map of String, Object
	private TypeReference<HashMap<String, Object>> typeReferenceMapObject = new TypeReference<HashMap<String, Object>>() {
	};

	public JsonConverter() {

		this.objectMapper
				.setVisibility(JsonMethod.FIELD, Visibility.ANY)
				.setVisibility(JsonMethod.SETTER, Visibility.NONE)
				.setVisibility(JsonMethod.GETTER, Visibility.NONE)
				.setVisibility(JsonMethod.IS_GETTER, Visibility.NONE)
				.configure(
						DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false)
				.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS,
						false)
				.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS,
						false);
		// this.objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
		// true);
	}

	/**
	 * Converts Supplied JSON to a Map<String, String>. Throws an exception in
	 * case the supplied JSON could not be parsed.
	 * 
	 * @param responseAsTxt
	 * @return
	 */
	public Map<String, String> convertJsonToMap(String responseAsTxt) {
		try {
			// parse incoming json into a map
			Map<String, String> map = objectMapper.readValue(responseAsTxt,
					typeReference);
			logger.debug("Response as Map: " + map);
			return map;
		} catch (IOException e) {
			logger.error("Received Data is not in valid format - "
					+ responseAsTxt, e);
			throw new UserNDotException(
					"Received Data is not in valid format - "
							+ getSubString(responseAsTxt, 100), e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T fromJson(String json, final TypeReference typeReference) {
		T data = null;
		try {
			data = objectMapper.readValue(json, typeReference);
			return data;
		} catch (IOException e) {
			logger.error("Received Data is not in valid format - " + json, e);
			throw new UserNDotException(
					"Received Data is not in valid format - "
							+ getSubString(json, 100), e);
		}
	}

	/**
	 * Converts Supplied JSON to a Map<String, String>. Throws an exception in
	 * case the supplied JSON could not be parsed.
	 * 
	 * @param responseAsTxt
	 * @return
	 */
	public Map<String, Object> convertJsonToObjectMap(String responseAsTxt) {
		try {
			// parse incoming json into a map
			Map<String, Object> map = objectMapper.readValue(responseAsTxt,
					typeReferenceMapObject);
			logger.debug("Response as Map: " + map);
			return map;
		} catch (IOException e) {
			logger.error("Received Data is not in valid format - "
					+ responseAsTxt, e);
			throw new UserNDotException(
					"Received Data is not in valid format - "
							+ getSubString(responseAsTxt, 100), e);
		}
	}

	public String convertMapToJson(Map<String, String> map) {

		String jsonValue = "{}";
		try {
			jsonValue = objectMapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			logger.error("Received Map is not in valid format - " + map, e);
			throw new UserNDotException(
					"Received Map is not in valid format - " + map, e);

		} catch (JsonMappingException e) {
			logger.error("Received Map is not in valid format - " + map, e);
			throw new UserNDotException(
					"Received Map is not in valid format - " + map, e);
		} catch (IOException e) {
			logger.error("Received Map is not in valid format - " + map, e);
			throw new UserNDotException(
					"Received Map is not in valid format - " + map, e);
		}
		return jsonValue;
	}

	public String toJson(Object object) {
		try {
			String jsonString = objectMapper.writeValueAsString(object);
			return jsonString;
		} catch (Exception e) {
			logger.error("Received object is not in valid format - " + object,
					e);
			throw new UserNDotException(
					"Received object is not in valid format - " + object, e);
		}
	}

	private String getSubString(String str, int maxLength) {
		if (str == null)
			return "";

		if (str.length() > 100)
			return str.substring(0, 100);
		else
			return str;
	}

	public <T> T convertJsonToObject(String json, Class<T> classType) {
		try {
			T converted = objectMapper.readValue(new StringReader(json),
					classType);

			return converted;
		} catch (Exception e) {
			throw new UserNDotException(
					"Server Error - Could not convert JSON to Object", e);
		}
	}

}
