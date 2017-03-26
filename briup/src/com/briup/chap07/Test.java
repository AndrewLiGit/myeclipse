package com.briup.chap07;

class Person{
	public static void say(){
		
		System.out.println("Person");
	}
}

class Students extends Person{
	
	public static void say(){
		
		System.out.println("Student");
	}
}
public class Test{
	
	public static void main(String args[]){
		
		String b = new String("abc");
		String a = "abc";
		String c = new String("abc");
		System.out.println(b.equals(a));
		System.out.println(b.equals(c));
		
		
		
		
		
//		String x = "xyz";
//		String z = x.toUpperCase();
//		System.out.println(z);
//		String y = z.replace("Y", "y");
//		System.out.println(y);
//		y = y+"abc";
//		System.out.println(y);
		
		
		
		
		
		
//		Person s = new Students();
//		
//		s.say();
		
		
//		byte i = 10;
//		i = (byte) (i + 1);
//		i++;
//		int[] a = new int[4];
//		for(int i=0;i<a.length;i++)
//			System.out.println(a[i]);
//		
//		int i=4;
//		Integer I=(Integer)i;
//		//boolean b=((Integer)i).equals(I);
//		//System.out.println(b);
//		Integer integer = new Integer(4);
//		boolean b=integer.equals(I);
//		System.out.println(integer.toString());
//		System.out.println(b);
	}
}
