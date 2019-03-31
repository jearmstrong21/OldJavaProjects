package co.megadodo.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HTest {

	public static void main(String[] args) throws FileNotFoundException {
		
		HCSSGenerator cssGen;
		HTheme theme;
		HLanguage lang;
		HTMLGen gen;
		
		theme = new HThemeEclipse();
//		theme = new HThemeWebstormDarcula();
//		theme = new HThemeWebstormDefault();
		
		cssGen = new HCSSGenerator(theme);
		lang = new HLanguageJava();

//		gen = HTMLGen.makeGen("input.txt");
//		gen = HTMLGen.makeGen("src/co/megadodo/v2/Vector.java");
//		gen = HTMLGen.makeGen("src/co/megadodo/v2/HTMLGen.java");
//		gen = HTMLGen.makeGen("src/co/megadodo/v2/HTest.java");
		
		gen = HTMLGen.makeGen(getFileName(new File(System.getProperty("user.dir"))));
		
		gen.smartSetLang();
		gen.setTheme(theme);
		gen.setCssGen(cssGen);
//		gen.setLang(lang);
		
		PrintWriter writer = new PrintWriter(new File("index.html"));
		gen.writeHTMLToFile(writer, "Title");
		writer.close();

	}
	
	public static Scanner sc;
	
	static {
		sc = new Scanner(System.in);
	}
	
	public static String getFileName(File curFile) {
		
		File[] files = curFile.listFiles();
		
		System.out.println("FILE: " + curFile.getPath());
		if(files == null) {
//			return curFile.getAbsolutePath();
			return curFile.getPath();
		}
		
		System.out.println("Select file by number: ");
		for(int i = 0; i < files.length; i++) {
			File f = files[i];
			String name = f.getName();
			if(f.isDirectory()) name += "/";
			System.out.println(String.format("% 5d", i) + " : " + f.getName());
		}
		
		System.out.print("Which file? ");
		int num = sc.nextInt();
		
		return getFileName(files[num]);
		
		
	}

}
