package com.briup.chap09;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiListener extends WindowAdapter 
implements MouseListener,MouseMotionListener{
	
	private JFrame jFrame;
	private Container contentPane;
	private JLabel jLabel = new JLabel();
	private JPanel jPanel = new JPanel();
	//³õÊ¼»¯ÈÝÆ÷
	public MultiListener() {
		jFrame = new JFrame("ListenerTest");
		contentPane = jFrame.getContentPane();
		contentPane.setBackground(Color.yellow);
		jFrame.setBounds(500,200,300,300);
		contentPane.add(jLabel,BorderLayout.NORTH);
		jLabel.setBackground(Color.red);
		//Í¸Ã÷¶È
		jLabel.setOpaque(true);
		contentPane.add(jPanel);
		jPanel.setBackground(Color.blue);
		//×¢²á¼àÌýÆ÷
		jPanel.addMouseListener(this);
		jPanel.addMouseMotionListener(this);
		jFrame.addWindowListener(this);
		jFrame.setDefaultCloseOperation
			(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing");
		System.exit(0);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouseDragged x="+x+" y="+y);
		jLabel.setText("mouseDragged x="+x+" y="+y);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouseMoved x="+x+" y="+y);
		jLabel.setText("mouseMoved x="+x+" y="+y);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouseClicked x="+x+" y="+y);
		jLabel.setText("mouseClicked x="+x+" y="+y);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mousePressed x="+x+" y="+y);
		jLabel.setText("mousePressed x="+x+" y="+y);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouseReleased x="+x+" y="+y);
		jLabel.setText("mouseReleased x="+x+" y="+y);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouseEntered x="+x+" y="+y);
		jLabel.setText("mouseEntered x="+x+" y="+y);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouseExited x="+x+" y="+y);
		jLabel.setText("mouseExited x="+x+" y="+y);
	}
	
	public static void main(String[] args) {
		new MultiListener();
	}
}
