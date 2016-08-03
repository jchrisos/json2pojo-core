package com.json2pojo;

import java.io.File;

import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;

import com.json2pojo.customs.CustomGenerationConfig;
import com.json2pojo.customs.CustomJackson2Annotator;
import com.json2pojo.customs.CustomRuleFactory;
import com.json2pojo.exceptions.InvalidJSONException;
import com.sun.codemodel.JCodeModel;

public class Generator {

	public void generateFiles(String json, String className, String baseDir, String packageName, String sourceType, boolean generateBuilders, boolean includeToString, boolean includeHashcodeAndEquals, boolean includeConstructors) throws InvalidJSONException {
		
		if (json == null || json.trim().length() == 0) {
			throw new InvalidJSONException();
		}
		
		try {
			
			CustomGenerationConfig config = new CustomGenerationConfig()
					.withSourceType(SourceType.valueOf(sourceType))
					.withBuilders(generateBuilders)
					.withConstructors(includeConstructors)
					.withHashcodeAndEquals(includeHashcodeAndEquals)
					.withToString(includeToString);
			
			SchemaMapper mapper = new SchemaMapper(new CustomRuleFactory(config, new CustomJackson2Annotator(), new SchemaStore()), new SchemaGenerator());

			JCodeModel codeModel = new JCodeModel();
			
			mapper.generate(codeModel, className, packageName, json);

			codeModel.build( new File(baseDir) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
