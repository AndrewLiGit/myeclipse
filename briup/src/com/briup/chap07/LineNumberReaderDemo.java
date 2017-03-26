package com.briup.chap07;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class LineNumberReaderDemo {
	public static void main(String[] args) throws IOException {
		FileReader fReader = new FileReader("src/com/briup/chap07/LineNumberReaderDemo.java");
		LineNumberReader lnr = new LineNumberReader(fReader);
		String line = null;
		lnr.setLineNumber(0);
		while((line=lnr.readLine())!=null){
			System.out.println(lnr.getLineNumber()+":"+line);
		}
		lnr.close();
	}
}
