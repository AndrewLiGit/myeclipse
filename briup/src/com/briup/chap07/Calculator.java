package com.briup.chap07;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame{
	private Container contentPane;
	private JTextField jtxt;
	private JLabel statusLbl;
	private JPanel panel;
	private JButton addBtn;
	private JButton subBtn;
	private JButton mulBtn;
	private JButton divBtn;
	private JButton eqlBtn;
	private JButton clrBtn;
	private JButton[] numBtn;
	
	public Calculator(){
		setTitle("Calculator");
		setSize(400,400);
		contentPane = getContentPane();	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		setVisible(true);
	}
	public void init(){
		contentPane.setLayout(new BorderLayout(5,5));
		jtxt = new JTextField(10);
		jtxt.setHorizontalAlignment(JTextField.RIGHT);
		statusLbl = new JLabel("Prepared");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
		addBtn = new JButton("+");		
		subBtn = new JButton("-");		
		mulBtn = new JButton("*");		
		divBtn = new JButton("/");		
		eqlBtn = new JButton("=");		
		clrBtn = new JButton("C");		
		numBtn = new JButton[10];		

		ActionListener btnListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = jtxt.getText();
				if(s.trim().length() == 0)
					return;
				if(e.getSource() == addBtn)
					jtxt.setText(s + "+");
				else if(e.getSource() == subBtn)
					jtxt.setText(s + "-");
				else if(e.getSource() == mulBtn)
					jtxt.setText(s + "*");
				else if(e.getSource() == divBtn)
					jtxt.setText(s + "/");
			}
		};	
		addBtn.addActionListener(btnListener);
		subBtn.addActionListener(btnListener);
		mulBtn.addActionListener(btnListener);
		divBtn.addActionListener(btnListener);
		/*
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = jtxt.getText();
				if(s.trim().length() == 0)
					return;
				jtxt.setText(s + "+");
			}
		});	
		subBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = jtxt.getText();
				if(s.trim().length() == 0)
					return;
				jtxt.setText(s + "-");
			}
		});	
		mulBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = jtxt.getText();
				if(s.trim().length() == 0)
					return;
				jtxt.setText(s + "*");
			}
		});	
		divBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = jtxt.getText();
				if(s.trim().length() == 0)
					return;
				jtxt.setText(s + "/");
			}
		});	
		*/
		clrBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jtxt.setText("");
				statusLbl.setText("Prepared");
			}
		});	
		eqlBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = jtxt.getText();
				if(s.trim().length() == 0)
					return;
				StringCalculator sc = new StringCalculator();
				String result = sc.calculateMain(s);
				jtxt.setText(result);
				statusLbl.setText("Calculated");
			}
		});	

		for(int i = 0; i < numBtn.length;i++){
			numBtn[i] = new JButton(i+"");
			final int j = i;
			numBtn[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String s = jtxt.getText();
					jtxt.setText(s + j);
				}
			});
		}

		for(int i = 1; i <= 16;i++){
			if(i == 4)
				panel.add(addBtn);
			else if(i >= 5 && i <= 7)
				panel.add(numBtn[i-1]);
			else if(i == 8)
				panel.add(subBtn);
			else if(i >= 9 && i <= 11)
				panel.add(numBtn[i - 2]);
			else if(i == 12)
				panel.add(mulBtn);
			else if(i == 13)
				panel.add(numBtn[0]);
			else if(i == 14)
				panel.add(clrBtn);
			else if(i == 15)
				panel.add(eqlBtn);
			else if(i == 16)
				panel.add(divBtn);
			else{
				panel.add(numBtn[i]);
			}
		}
			
		add(jtxt,BorderLayout.NORTH);	
		add(statusLbl,BorderLayout.SOUTH);
		add(panel,BorderLayout.CENTER);
	}
	public static void main(String[] args){
		new Calculator();
	}
}

class StringCalculator {
  //主计算方法参数为一个混合运算字符串
 public static String calculateMain(String str){
  String []str1=str.split("[+-]");   //将字符串用“+-”拆分
  for(int i=0;i<str1.length;i++){    //若拆分的子串包含“*/”高级运算，怎调用高级运算函数
   if(str1[i].indexOf("/")>0||str1[i].indexOf("*")>0){
    str1[i]=caculateUnder(str1[i]);
   }
  }  
  char stdfc[]=str.toCharArray();    //将次字符串转成字符数组
  char stdf[]=new char[50];     //用于存放字符数组中符号
  int j=0,count=1;  
  for(int i=0;i<stdfc.length;i++){   //记录字符数组中的运算符号及其次序
    if(stdfc[i]=='+')
    {stdf[j]='+';j++;count++;}
   if(stdfc[i]=='-')
       {stdf[j]='-';j++;count++;}    
  }
  double stdd[]=new double[50];  
  for(int i=0;i<str1.length;i++)    //将子串转换成实数
   stdd[i]=Double.parseDouble(str1[i]);
  for(int i=1;i<stdf.length;i++){    //将转换成的实数数组结合记录的符号数组计算结果
   if(stdf[i-1]=='+')
    stdd[0] += stdd[i];  
   if(stdf[i-1]=='-')
    stdd[0] -=stdd[i];
  }
  Double d=new Double(stdd[0]);
  String result=d.toString();
  return result; //返回值为计算结果的字符串对象
 }
  //此方法为私有方法只有主方法caculateMain()能调用；此方法用于计算含有“*/”高级字符串
 private static String caculateUnder(String str){//此函数用于计算包含“/*”高级运算
  //String stds[];
  char stdf[]=new char[50];     //用于记录所传字符串中的运算符号
  double stdd[]=new double[50];    //用于存放所拆分成的字符串的实数转换
  String stds[]=str.split("[/*]");      //拆分所传的字符串参数
  for(int i=0;i<stds.length;i++)    //将分开的子串转换成实数
   stdd[i]=Double.parseDouble(stds[i]);
  char stdfc[]=str.toCharArray();    //将所传字符串转成字符数组
  int j=0,count=1;  
  for(int i=0;i<stdfc.length;i++){   //用数组stdf[]记录字符数组中运算符号的位置
    if(stdfc[i]=='/')
    {stdf[j]='/';j++;count++;}
   if(stdfc[i]=='*')
       {stdf[j]='*';j++;count++;}    
  } 
  for(int i=1;i<count;i++){     //根据转换成的实数数组和记录符号的数组来计算
   if(stdf[i-1]=='/')
    stdd[0] /= stdd[i];
   if(stdf[i-1]=='*')
    stdd[0] *=stdd[i]; 
  }
  Double d =new Double(stdd[0]);
  String result=d.toString();
  return result;        //返回值为结果的字符串对象
 }
 
 //此函数用于计算包含括号的字符串
 
 public static String caculateHigh(String str){ 
  String []str1;
  str1=str.split("[(]");      //用左括号将所传参数分隔
  String str2;
  str2=calculateMain(str1[str1.length-1]);  //调用住计算方法  
  String str3;
  str3="("+str1[str1.length-1];
  str=str.replace(str3, str2);    //将括号中的结果替换掉原来括号的位置
  return str;
 }
/* 
 public static void main(String[] args) {
  System.out.println(calculateMain("3*4/2+5*4/2+3-4+9*2/4*2"));
 }
*/
}
