package com.briup.chap09;

//�¼�����
public class EmotionEvent {
	//�¼�Դ
	private Object source;
	private String info;
	
	public EmotionEvent(Object source) {
		super();
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
