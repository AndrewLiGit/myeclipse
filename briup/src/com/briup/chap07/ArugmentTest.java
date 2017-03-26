package com.briup.chap07;

public class ArugmentTest{
	static class IntA{
	  private int a;
	  public IntA(int a){ this.a = a; }
	  public int getA() { return a; }
	  public void setA(int a){ this.a = a; }
	}
	static public void change(int a){
	  a = 3;
	}
	static public void change(IntA a){
	  a.setA(3);
		
	}
	static public void changeRef(IntA a){
	  a = new IntA(9);
	  System.out.println(a.getA());
	}
	public static void main(String[] args){
	   int a = 2;
	   change(a);
	   System.out.println(a);
	   IntA ia = new IntA(5);
	   change(ia);
	   System.out.println(ia.getA());
	   changeRef(ia);
	   System.out.println(ia.getA());
	}
}

