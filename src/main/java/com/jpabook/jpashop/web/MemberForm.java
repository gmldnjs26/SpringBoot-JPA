package com.jpabook.jpashop.web;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberForm {
	@NotEmpty(message = "名前は必須です")
	private String name;
	private String city;
	private String street;
	private String zipcode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
