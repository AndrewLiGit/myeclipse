package com.briup.bean;


public class Contactinfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	// Fields
	private Integer contactid;
	private User user;
	private String street1;
	private String street2;
	private String city;
	private String province;
	private String country;
	private String zip;
	private String email;
	private String homephone;
	private String cellphone;
	private String officephone;
	
	
	// Constructors
	
	/** default constructor */
	public Contactinfo() {
	}
	
	/** full constructor */
	public Contactinfo(User user,String street1,String street2,String city,String province,
			String country,String zip,String email,String homephone,String cellphone,
			String officephone) {
		this.user = user;
		this.street1 = street1;
		this.street2 = street2;
		this.city  = city;
		this.province = province;
		this.country = country;
		this.zip  = zip;
		this.email = email;
		this.homephone = homephone;
		this.cellphone = cellphone; 
		this.officephone = officephone;
	}

	// Property accessors
	public Integer getContactid() {
		return contactid;
	}

	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

}
