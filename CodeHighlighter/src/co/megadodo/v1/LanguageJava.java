package co.megadodo.v1;

import java.util.Arrays;

public class LanguageJava extends Language {
	
	public LanguageJava() {
		codewords.addAll(Arrays.<String>asList(
				// key variables/types/methods
				"ArrayList",
				"Boolean",
				"Byte",
				"Character",
				"Class",
				"Double",
				"Enum",
				"Float",
				"Integer",
				"Long",
				"Math",
				"Number",
				"Object",
				"String",
				"StringBuffer",
				"StringBuilder",
				"System",
				"Thread",
				"Throwable",
				"Exception"				));
		keywords.addAll((Arrays.<String>asList(
				
				"abstract",
				"assert",
				"boolean",
				"break",
				"byte",
				"case",
				"catch",
				"char",
				"class",
				"continue",
				"default",
				"do",
				"double",
				"else",
				"enum",
				"extends",
				"final",
				"finally",
				"float",
				"for",
				"if",
				"implements",
				"import",
				"instanceof",
				"int",
				"interface",
				"long",
				"native",
				"new",
				"package",
				"private",
				"protected",
				"public",
				"return",
				"short",
				"static",
				"strictfp",
				"super",
				"switch",
				"synchronized",
				"this",
				"throw",
				"throws",
				"transient",
				"try",
				"void",
				"volatile",
				"while",
				"true",
				"null",
				"false",
				"const",
				"goto"
				)));
	}
	
	@Override
	public String format(String str) {
		return str;
	}
	
}
