package com.userndot.common.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.hamcrest.Factory;
import org.springframework.stereotype.Component;

@Component
public class JacksonProcessorConfig {


	    @Factory
	    public ObjectMapper mapper() {
	        ObjectMapper newObjectMapper = new ObjectMapper();
	        newObjectMapper.setVisibilityChecker(
	                newObjectMapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY).withGetterVisibility(Visibility.NONE)
	                        .withSetterVisibility(Visibility.NONE).withIsGetterVisibility(Visibility.NONE)
	                );



	        // newObjectMapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
	        newObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        newObjectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, false);
	        newObjectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false);

	        return newObjectMapper;
	    }
	
}
