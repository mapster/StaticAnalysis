package org.mapster.ast.demo;

public class Mains<E> {
	int x;
	
	static int x2 = 2;
	
	public int metode(){
		return 0;
	}
	
	public static void main(String[][] args){
		Mains m = new Mains();
		m.method("a", 2);
		System.out.println(args[1][2]);
	}
	
	public Mains(){
		
	}
	
	public void method(String streng, int tall){
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		a++;
		while(x < 10){
			System.out.println("hei");
			b--;
			while(c < 100){
				++c;
				--d;
			}
			z += 1;
			y = y +1;
		}
	}
	
	
	
	public class Bla1 {
		
	}
}

class Bla {

}
