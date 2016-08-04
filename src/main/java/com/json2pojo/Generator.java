package com.json2pojo;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;

import com.json2pojo.customs.CustomGenerationConfig;
import com.json2pojo.customs.CustomJackson2Annotator;
import com.json2pojo.customs.CustomRuleFactory;
import com.json2pojo.exceptions.InvalidContentException;
import com.sun.codemodel.JCodeModel;

public class Generator {

	private static final String URL_PATTERN = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
	
	public void generateFiles(String content, String className, String baseDir, String packageName, boolean generateBuilders, boolean includeToString, boolean includeHashcodeAndEquals, boolean includeConstructors) throws InvalidContentException {
		
		if (content == null || content.trim().length() == 0) {
			throw new InvalidContentException();
		}
		
		try {
			
			if (isUrl(content)) {
				content = getJsonFromUrl(content);
			}
			
			CustomGenerationConfig config = new CustomGenerationConfig()
					.withSourceType(SourceType.JSON)
					.withBuilders(generateBuilders)
					.withConstructors(includeConstructors)
					.withHashcodeAndEquals(includeHashcodeAndEquals)
					.withToString(includeToString);
			
			SchemaMapper mapper = new SchemaMapper(new CustomRuleFactory(config, new CustomJackson2Annotator(), new SchemaStore()), new SchemaGenerator());

			JCodeModel codeModel = new JCodeModel();
			
			mapper.generate(codeModel, className, packageName, content);

			codeModel.build( new File(baseDir) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean isUrl(String url) {
		
		Matcher matcher = Pattern.compile(URL_PATTERN).matcher(url);
		
		return matcher.matches();
		
	}
	
	private String getJsonFromUrl(String url) throws ClientProtocolException, IOException {
		
		DefaultHttpClient client = new DefaultHttpClient();
		
		HttpResponse response = client.execute( new HttpGet(url) );
		
		return IOUtils.toString( response.getEntity().getContent() );
		
	}
	
}
