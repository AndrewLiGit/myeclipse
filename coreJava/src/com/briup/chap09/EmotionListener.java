package com.briup.chap09;

import java.util.EventListener;

public interface EmotionListener extends EventListener{
	public void happy(EmotionEvent e);
	public void sad(EmotionEvent e);
}
//package com.briup.jd1410.chap09;
//
////�¼�������
//public interface EmotionListener {
//	public void happy(EmotionEvent e);
//	public void sad(EmotionEvent e);
//}
