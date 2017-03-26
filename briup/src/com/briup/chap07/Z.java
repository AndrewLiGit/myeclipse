package com.briup.chap07;

import java.util.Calendar;

class A{
	
}

public class Z extends A{
	public void get(){
		System.out.println(this.getClass());
		System.out.println(super.getClass());
	}
	public static void main(String[] args) {
//		new Z().get();
//		float b=0.2f;
//		float c=3;
//		float e=3.0f;
//		long d=3;
//		double a=0.2;
//		
//		a=a/0;
//		System.out.println(a);
		
		String string = new String("abc");
		System.out.println(string.length());
		int[] a = new int[3];
		int b = a.length;
		System.out.println(b);
		
	}
}

//class A{
////	public A(){}
//	public A(String mess){
//		System.out.println(mess);
//	}
//}
//
//class B {
//	private  A a = new A("B A");
//	public B(){
//		System.out.println("mess");
//	}
//	static {
//		A a = new A("B¡¡A static ");
//	}
//}
//
//public class Z extends B{
//	public Z() {
//		System.out.println("  Z  ¹¹ÔìÆ÷");
//	}
//	static{
//		A a = new A("Z¡¡A static ");
//	}
//	private A a = new A("B A   Z");
//	public static void main(String[] args) {
//		new Z();
//	}
//} 



//class X {
//	Y b = new Y();//  1
//	X() {
//			System.out.print("X");// 2
//	}
//}
//
//class Y {
//	Y() {
//			System.out.print("Y");
//	}
//}
//
//public class Z extends X {
//	Y y = new Y();//   3
//	Z() {
//			System.out.print("Z");//   4
//	}
//
//	public static void main(String[] args) {
//
////		int i = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
////		System.out.println(i);
//		
////		new Z();
////		float f;
////		f = 42.3f;
////		float e = -412;
////		double d = 34;
////		byte b = 127;
////		//double d = 0x12345678;
////		//int other = (int) true;
////		int a= 'a'+5;
////		System.out.println(a);
////		int $abc;
////		String string;
////		//int a = 1;
////		if(a<1)string="asdf";
////		string="asfljdf";
////		System.out.println(string.length());
//	}
//}
