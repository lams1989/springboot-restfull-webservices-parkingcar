package com.lams1989.rest.exercise.beans;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class ParkingCar {

	private Integer id;

	@Size(min = 6, max = 10, message = "the registry me must be between 6 and 10 characters")
	private String registry;

	@Size(min = 2, max = 15, message = "the registry me must be between 2 and 15 characters")
	private String brand;

	@Size(min = 3, max = 50, message = "the registry me must be between 3 and 50 characters")
	private String ownersname;

	@Past
	private Date hourin;

	@Min(value = 10, message = "the payment me must be Min 10 ")
	@Max(value = 10000, message = "the payment me must be Max 10000 ")
	private long payment;

	public ParkingCar(Integer id, String registry, String brand, String ownersname, Date hourin, long payment) {
		super();
		this.id = id;
		this.registry = registry;
		this.brand = brand;
		this.ownersname = ownersname;
		this.hourin = hourin;
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOwnersname() {
		return ownersname;
	}

	public void setOwnersname(String ownersname) {
		this.ownersname = ownersname;
	}

	public Date getHourin() {
		return hourin;
	}

	public void setHourin(Date hourin) {
		this.hourin = hourin;
	}

	public long getPayment() {
		return payment;
	}

	public void setPayment(long payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "ParkingCar [id=" + id + ", registry=" + registry + ", brand=" + brand + ", ownersname=" + ownersname
				+ ", hourin=" + hourin + ", payment=" + payment + "]";
	}

}
