package com.briup.composite;

import java.io.Serializable;

//���������: 
//����ʵ�����л��ӿ�  ���������ʱ��ᱨ��
//�����дhashCode(������ʹ��Object���е�Ҳ����)
//equals����һ��Ҫ��д  ��Ϊ�Ƚ������Ƿ���ȵ�ʱ����Ҫ�õ�����������
public class PersonPK implements Serializable{
	private static final long serialVersionUID = 3223722820575289771L;
	private long id;
	private String name;
	
	public PersonPK() {
		
	}
	public PersonPK(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		return (int)id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this==obj){
			return true;
		}
		if(!(obj instanceof PersonPK)){
			return false;
		}
		
		PersonPK pk = (PersonPK) obj;
		
		if(this.id == pk.id&&this.name.equals(pk.name)){
			return true;
		}
		return false;
	}
	
	
}
