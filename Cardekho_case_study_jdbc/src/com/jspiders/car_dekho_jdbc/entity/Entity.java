package com.jspiders.car_dekho_jdbc.entity;

public class Entity {
	private int carId;
	private String carName;
	private String carBrand;
	private String fuleType;
	private int price;

	
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getFuleType() {
		return fuleType;
	}
	public void setFuleType(String fuleType) {
		this.fuleType = fuleType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Entity [carId=" + carId + ", carName=" + carName + ", carBrand=" + carBrand + ", fuleType=" + fuleType
				+ ", price=" + price + "]";
	}
	

}
