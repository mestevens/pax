package ca.mestevens.java.pax.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mestevens.java.pax.utils.PlatformTypeConverterUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaxParam {

	private String type;
	private String name;
	private String description;
	private String modifier;
	
	public String getJavaString() {
		String javaString = "";
		String methodParam = PlatformTypeConverterUtil.getJavaType(type);
		if (modifier != null && !modifier.isEmpty()) {
			javaString += PlatformTypeConverterUtil.getJavaModifier(modifier) + " ";
		}
		javaString += methodParam + " " + name;
		return javaString;
	}
	
	public String getJavaDocumentation() {
		if (description == null || description.equals("")) {
			return null;
		}
		String documentation = " * @param " + name + " ";
		final int wordsPerLine = 15;
		List<String> descriptionStrings = new ArrayList<String>(Arrays.asList(description.split(" ")));
		int i = 0;
		for (String descriptionString : descriptionStrings) {
			if (i % wordsPerLine == 0 && i != 0) {
				documentation += " * ";
			}
			documentation += descriptionString;
			if (i % wordsPerLine == wordsPerLine - 1) {
				documentation += "\n";
			} else if (i < descriptionStrings.size() - 1){
				documentation += " ";
			} else {
				documentation += "\n";
			}
			i++;
		}
		return documentation;
	}
	
	public String getObjcString() {
		String methodParam = PlatformTypeConverterUtil.getObjcType(type);
		if (modifier != null && !modifier.isEmpty()) {
			methodParam += " " + PlatformTypeConverterUtil.getObjcModifier(modifier);
		}
		return "(" + methodParam + ")" + name;
	}
	
}
