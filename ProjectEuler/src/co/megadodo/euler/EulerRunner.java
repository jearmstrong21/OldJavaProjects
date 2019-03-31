package co.megadodo.euler;

import java.util.Scanner;

public class EulerRunner implements EulerUtils {
//TODO
	
	public static void main(String[] args) throws Throwable {
		new EulerRunner();
	}
	
	public EulerRunner() throws Throwable {
		Scanner sc = new Scanner(System.in);
		print("Which euler? ");
		int i = sc.nextInt();
//		Euler euler = new Euler();
//		switch(i) {
//			case 7: euler = new Euler0007(); break;
//			case 8: euler = new Euler0008(); break;
//		}
		String className = String.format("Euler%04d", i);
		Class<Euler> clazz = (Class<Euler>)Class.forName("co.megadodo.euler." + className);
		Euler euler = (Euler) clazz.newInstance();
		println(className);
		euler.run();
		
		sc.close();
	}
	
}
