package co.megadodo.euler;

public class Euler0023 extends Euler {

	public void run() {
		
		int counter = 0;
		int n = 10;
		//abcdefghij
		for(int a=0;a<n;a++){
			for(int b=0;b<n;b++){
				if(containsRepeats(a,b)) continue;
				for(int c=0;c<n;c++) {
					if(containsRepeats(a,b,c)) continue;
					for(int d=0;d<n;d++) {
						if(containsRepeats(a,b,c,d)) continue;
						for(int e=0;e<n;e++) {
							if(containsRepeats(a,b,c,d,e)) continue;
							for(int f=0;f<n;f++) {
								if(containsRepeats(a,b,c,d,e,f)) continue;
								for(int g=0;g<n;g++) {
									if(containsRepeats(a,b,c,d,e,f,g)) continue;
									for(int h=0;h<n;h++) {
										if(containsRepeats(a,b,c,d,e,f,g,h)) continue;
										for(int i=0;i<n;i++) {
											if(containsRepeats(a,b,c,d,e,f,g,h,i)) continue;
											for(int j=0;j<n;j++) {
												if(containsRepeats(a,b,c,d,e,f,g,h,i,j)) continue;
												
												counter++;
												if(counter == MIL) {
													print(a);
													print(b);
													print(c);
													print(d);
													print(e);
													print(f);
													print(g);
													print(h);
													print(i);
													print(j);
													println();
													exit();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		println(counter);
		
	}
	
}
