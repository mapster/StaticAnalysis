package org.mapster.ast.demo;

public class Main<E> {
	int x;
	
	static int x2 = 4;
	
	public int metode(){
		return 0;
	}
	
	public static <T> void main(String[] args){
		Main m = new Main();
//		m.method("a");
	}
	
	public Main(){
		
	}
	
	public <T> void method(String s){
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		while(x < 10){
			System.out.println("hei");
			a++;
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
