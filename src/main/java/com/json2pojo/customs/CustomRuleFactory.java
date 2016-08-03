package com.json2pojo.customs;

import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

public class CustomRuleFactory extends RuleFactory {

	public CustomRuleFactory(CustomGenerationConfig customGenerationConfig,
			CustomJackson2Annotator customJackson2Annotator, SchemaStore schemaStore) {
		super(customGenerationConfig, customJackson2Annotator, schemaStore);
	}

}
