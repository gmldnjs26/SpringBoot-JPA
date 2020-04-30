package com.jpabook.jpashop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
	protected Address() {
		
	}
	// Setterを除去してコンストラクタで値を全部初期化させて変更できないクラスにしましょう！
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public String getStreet() {
		return street;
	}
	public String getZipcode() {
		return zipcode;
	}
	
}
