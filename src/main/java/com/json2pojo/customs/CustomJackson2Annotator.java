package com.json2pojo.customs;

import org.jsonschema2pojo.Jackson2Annotator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;

public class CustomJackson2Annotator extends Jackson2Annotator {

	@Override
    public void propertyInclusion(JDefinedClass clazz, JsonNode schema) {
        clazz.annotate(JsonIgnoreProperties.class).param("ignoreUnknown", true);
    }
	
	@Override
	public void propertyOrder(JDefinedClass clazz, JsonNode propertiesNode) {

	}
	
	@Override
	public void propertySetter(JMethod setter, String propertyName) {

	}
	
	@Override
	public void propertyGetter(JMethod getter, String propertyName) {

	}
	
	@Override
	public boolean isAdditionalPropertiesSupported() {
		return false;
	}
	
}
