package com.naikara_talk.dto;

public class SampleDto {
	private int updaded = 0;

	public int getUpdaded() {
		return updaded;
	}

	public void setUpdaded(int updaded) {
		this.updaded = updaded;
	}

	private int type;
	private String name = "";
	private int price;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
