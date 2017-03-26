package com.briup.chap09;

public class EmotionTest {
	public static void main(String[] args) {
		Gril gril = new Gril();
		gril.addEmotionListener(new EmotionListener() {
			
			@Override
			public void sad(EmotionEvent e) {
				Gril g = (Gril) e.getSource();
				System.out.println("I am sad too because "
							+g.getName()+" said "+e.getInfo() );
			}
			
			@Override
			public void happy(EmotionEvent e) {
				Gril g = (Gril) e.getSource();
				System.out.println("I am happy too because "
						+g.getName()+" said "+e.getInfo() );
			}
		});
		gril.happy();
		gril.sad();
	}
}
