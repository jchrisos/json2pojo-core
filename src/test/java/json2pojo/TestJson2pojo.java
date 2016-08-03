package json2pojo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.json2pojo.Generator;
import com.json2pojo.exceptions.InvalidJSONException;

public class TestJson2pojo {

	private String json;
	
	private String jsonSchema;
	
	private String className;
	
	private String packageName;
	
	private String baseDir;
	
	private Generator generator;
	
	private String sourceType;
	
	private boolean generateBuilders;
	
	private boolean includeToString;
	
	private boolean includeHashcodeAndEquals;
	
	private boolean includeConstructors;
	
	@Before
	public void setup() throws IOException {
		
		InputStream in = this.getClass().getResourceAsStream("/testjson.json");
		
		json = IOUtils.toString(in, "UTF-8");
		
		in = this.getClass().getResourceAsStream("/testschema.json");
		
		jsonSchema = IOUtils.toString(in, "UTF-8");
		
		className = "Person";
		
		baseDir = "/Users/juliochrisostomo/Desktop/";

		packageName = "com.examples";
		
		sourceType = "JSON";
		
		generateBuilders = true;
		
		generator = new Generator();

	}

	@Test(expected = InvalidJSONException.class)
	public void shouldThrowsAnInvalidJSONException() throws InvalidJSONException {
		
		generator.generateFiles(null, className, baseDir, packageName, sourceType, generateBuilders, includeToString, includeHashcodeAndEquals, includeConstructors);
		generator.generateFiles("", className, baseDir, packageName, sourceType, generateBuilders, includeToString, includeHashcodeAndEquals, includeConstructors);
		
	}
	
	@Test
	public void shouldGeneratePOJOsFromJSON() throws InvalidJSONException {
		
		generator.generateFiles(json, className, baseDir, packageName, sourceType, generateBuilders, includeToString, includeHashcodeAndEquals, includeConstructors);
		
		File file = new File(baseDir + packageName.replace(".", "/"));

		Assert.assertTrue( ArrayUtils.contains(file.list() , "Person.java") && ArrayUtils.contains(file.list() , "PhoneNumber.java") );
		
	}
	
	@Test
	public void shouldGeneratePOJOsFromJSONSchema() throws InvalidJSONException {
		
		generator.generateFiles(jsonSchema, className, baseDir, packageName, "JSONSCHEMA", generateBuilders, includeToString, includeHashcodeAndEquals, includeConstructors);
		
		File file = new File(baseDir + packageName.replace(".", "/"));

		Assert.assertTrue( ArrayUtils.contains(file.list() , "Person.java") && ArrayUtils.contains(file.list() , "PhoneNumber.java") );
		
	}
	
	@After
	public void clear() throws IOException {
		
		File file = new File(baseDir + "/com");
		
		FileUtils.deleteDirectory(file);
		
	}
	
}
