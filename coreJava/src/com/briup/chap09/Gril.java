package com.briup.chap09;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//事件源
public class Gril {
	private String name;
	private Set<EmotionListener> boys;
	
	public Gril(String name) {
		super();
		this.name = name;
	}

	public Gril() {
		this("lucy");
		boys = new HashSet<EmotionListener>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmotionListener> getBoys() {
		return boys;
	}

	public void setBoys(Set<EmotionListener> boys) {
		this.boys = boys;
	}
	//添加监听器
	public void addEmotionListener(EmotionListener e) {
		boys.add(e);
	}
	public void removeEmotionListener(EmotionListener e) {
		boys.add(e);
	}
	public void happy(){
		EmotionEvent e = new EmotionEvent(this);
		e.setInfo("I am happy");
		Iterator<EmotionListener> iter =
				boys.iterator();
		while (iter.hasNext()) {
			EmotionListener boy = iter.next();
			boy.happy(e);
		}
	}
	public void sad(){
		EmotionEvent e = new EmotionEvent(this);
		e.setInfo("I am sad");
		Iterator<EmotionListener> iter =
				boys.iterator();
		while (iter.hasNext()) {
			EmotionListener boy = iter.next();
			boy.sad(e);
		}
	}
}

