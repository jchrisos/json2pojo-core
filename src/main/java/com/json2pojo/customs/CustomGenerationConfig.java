package com.json2pojo.customs;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.SourceType;

public class CustomGenerationConfig extends DefaultGenerationConfig {

	private SourceType sourceType;

	private boolean generateBuilders;
	
	private boolean includeToString;
	
	private boolean includeHashcodeAndEquals;
	
	private boolean includeConstructors;
	
	public CustomGenerationConfig withSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
		return this;
	}
	
	public CustomGenerationConfig withBuilders(boolean generateBuilders) {
		this.generateBuilders = generateBuilders;
		return this;
	}
	
	public CustomGenerationConfig withToString(boolean includeToString) {
		this.includeToString = includeToString;
		return this;
	}
	
	public CustomGenerationConfig withHashcodeAndEquals(boolean includeHashcodeAndEquals) {
		this.includeHashcodeAndEquals = includeHashcodeAndEquals;
		return this;
	}
	
	public CustomGenerationConfig withConstructors(boolean includeConstructors) {
		this.includeConstructors = includeConstructors;
		return this;
	}
	
	@Override
	public SourceType getSourceType() {
		return sourceType;
	}
	
	@Override
	public boolean isGenerateBuilders() {
		return generateBuilders;
	}
	
	
	@Override
	public boolean isIncludeToString() {
		return includeToString;
	}
	
	@Override
	public boolean isIncludeHashcodeAndEquals() {
		return includeHashcodeAndEquals;
	}
	
	@Override
	public boolean isIncludeAdditionalProperties() {
		return includeConstructors;
	}
	
	@Override
	public boolean isIncludeConstructors() {
		return false;
	}
	
	@Override
	public boolean isIncludeDynamicAccessors() {
		return false;
	}
	
}
