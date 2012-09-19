package org.mapster.ast.demo;

public class Main {
	int x;
	
	public static void main(String[] args){
		Main m = new Main();
		m.method("a");
	}
	
	public void method(String s){
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
}
